package org.samaelheaven.nebula.core;

import org.samaelheaven.nebula.network.Request;
import org.samaelheaven.nebula.network.Response;

public abstract class Application {

    public abstract void handleRequest(Request request, Response response);
}