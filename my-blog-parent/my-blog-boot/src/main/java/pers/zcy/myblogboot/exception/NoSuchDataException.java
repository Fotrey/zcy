package pers.zcy.myblogboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchDataException extends RuntimeException {
    public NoSuchDataException() {
        super();
    }

    public NoSuchDataException(String message) {
        super(message);
    }

    public NoSuchDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
