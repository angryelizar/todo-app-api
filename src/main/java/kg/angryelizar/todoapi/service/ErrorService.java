package kg.angryelizar.todoapi.service;

import kg.angryelizar.todoapi.dto.ErrorResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ErrorService {
    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);
}
