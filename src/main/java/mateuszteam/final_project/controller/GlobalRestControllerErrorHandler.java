package mateuszteam.final_project.controller;

import mateuszteam.final_project.domain.dto.ErrorResponse;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    ErrorResponse handleOfferNotFoundException(final ResourceNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    ErrorResponse handleUsernameNotFoundException(final UserNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
