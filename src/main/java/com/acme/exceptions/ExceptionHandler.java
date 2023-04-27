package com.acme.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.jboss.logging.Logger;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;


@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(ExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof BadRequestException
                || exception instanceof InvalidFormatException
                || exception instanceof JsonParseException
                || exception instanceof IllegalArgumentException) {
            LOG.error(exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponseBody(Response.Status.BAD_REQUEST, exception.getMessage()))
                    .build();
        }

        if(exception instanceof NotFoundException) {
            LOG.error(exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponseBody(Response.Status.NOT_FOUND, exception.getMessage()))
                    .build();
        }

        if (LOG.isDebugEnabled()) {
            LOG.error(Arrays.toString(exception.getStackTrace()));
        } else {
            LOG.error(exception);
        }

        LOG.errorf("Internal Server Error - %s ", exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponseBody(Response.Status.INTERNAL_SERVER_ERROR,"Something unexpected happened. Try again"))
                .build();

    }

    public static final class ErrorResponseBody {

        private final Response.Status status;
        private final String message;

        public ErrorResponseBody(Response.Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public Response.Status getStatus() { return status; }
        public String getMessage() {
            return message;
        }
    }
}
