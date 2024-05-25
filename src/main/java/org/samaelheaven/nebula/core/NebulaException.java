package org.samaelheaven.nebula.core;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

public class NebulaException extends RuntimeException {
    public NebulaException() {
        super();
    }

    public NebulaException(@Nullable @NonNls String message) {
        super(message);
    }

    public NebulaException(@Nullable @NonNls String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    public NebulaException(@Nullable Throwable cause) {
        super(cause);
    }

    protected NebulaException(@Nullable @NonNls String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}