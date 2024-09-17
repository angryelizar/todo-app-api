package kg.angryelizar.todoapi.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    private ErrorResponse noSuchElementHandler(NoSuchElementException exception) {
        return ErrorResponse.builder(exception, HttpStatus.NO_CONTENT, exception.getMessage()).build();
    }
}
