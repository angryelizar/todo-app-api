package kg.angryelizar.todoapi.exception.handler;

import kg.angryelizar.todoapi.dto.ErrorResponseBody;
import kg.angryelizar.todoapi.exception.TaskException;
import kg.angryelizar.todoapi.exception.UserException;
import kg.angryelizar.todoapi.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private ErrorResponseBody methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return errorService.makeResponse(exception.getBindingResult());
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    private ErrorResponseBody userException(UserException exception){
        return errorService.makeResponse(exception);
    }

    @ExceptionHandler(TaskException.class)
    @ResponseBody
    private ErrorResponseBody taskException(TaskException exception){
        return errorService.makeResponse(exception);
    }
}
