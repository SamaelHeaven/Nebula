package org.samaelheaven.nebula.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import org.jetbrains.annotations.NotNull;
import org.samaelheaven.nebula.core.NebulaException;
import org.samaelheaven.nebula.utils.Config;
import org.samaelheaven.nebula.utils.Resource;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public final class Server implements HttpHandler {
    private final HttpsServer httpsServer;
    private final ResponseBuilder responseBuilder;

    public Server(@NotNull ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
        var sslContext = buildSSLContext();
        try {
            httpsServer = HttpsServer.create(new InetSocketAddress(443), 0);
            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
            httpsServer.createContext("/", this);
        } catch (Exception e) {
            throw new NebulaException(e);
        }
    }

    @Override
    public void handle(@NotNull HttpExchange exchange) {
        try (exchange) {
            var request = new Request(exchange);
            var response = responseBuilder.buildResponse(request);
            var compress = false;
            if (request.getHeaders().containsKey("Accept-Encoding")) {
                for (var header : request.getHeaders().get("Accept-Encoding")) {
                    if (header.contains("gzip")) {
                        response.getHeaders().set("Content-Encoding", "gzip");
                        compress = true;
                        break;
                    }
                }
            }
            var body = (compress ? compressBody(response.getBody()) : response.getBody());
            exchange.getResponseHeaders().putAll(response.getHeaders());
            exchange.sendResponseHeaders(response.getCode(), body.length);
            exchange.getResponseBody().write(body);
        } catch (IOException e) {
            throw new NebulaException(e);
        }
    }

    private byte[] compressBody(byte[] body) {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(body);
            gzipOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new NebulaException(e);
        }
    }

    public void start() {
        httpsServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        if (GraphicsEnvironment.isHeadless() || !Desktop.isDesktopSupported()) {
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE) && Config.getBoolean("OPEN_BROWSER", true)) {
            try {
                desktop.browse(new java.net.URI("https://localhost"));
            } catch (Exception e) {
                throw new NebulaException(e);
            }
        }
    }

    public void stop() {
        httpsServer.stop(0);
    }

    private SSLContext buildSSLContext() {
        try {
            var password = Config.getString("KEYSTORE_PASSWORD").toCharArray();
            var ks = KeyStore.getInstance("JKS");
            ks.load(Objects.requireNonNull(Resource.inputStream("environment/keystore.jks")), password);
            var kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);
            var sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            return sslContext;
        } catch (Exception e) {
            throw new NebulaException(e);
        }
    }
}