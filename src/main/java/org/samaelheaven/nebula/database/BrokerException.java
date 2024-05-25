package org.samaelheaven.nebula.database;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.samaelheaven.nebula.core.NebulaException;

public class BrokerException extends NebulaException {
    public BrokerException() {
        super();
    }

    public BrokerException(@Nullable @NonNls String message) {
        super(message);
    }

    public BrokerException(@Nullable @NonNls String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    public BrokerException(@Nullable Throwable cause) {
        super(cause);
    }

    protected BrokerException(@Nullable @NonNls String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}