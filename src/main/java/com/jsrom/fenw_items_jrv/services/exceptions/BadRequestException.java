package com.jsrom.fenw_items_jrv.services.exceptions;

public class BadRequestException extends RuntimeException  {
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
