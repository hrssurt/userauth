package com.userauth.service.exception;

public class RoleExistException extends ServiceException {
    public RoleExistException() {
        super();
    }

    public RoleExistException(String message) {
        super(message);
    }

    public RoleExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleExistException(Throwable cause) {
        super(cause);
    }

    protected RoleExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
