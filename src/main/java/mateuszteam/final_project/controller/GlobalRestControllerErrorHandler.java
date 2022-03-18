package mateuszteam.final_project.controller;

import mateuszteam.final_project.domain.dto.ErrorResponse;
import mateuszteam.final_project.domain.dto.NotEnoughFreeCopiesErrorResponse;
import mateuszteam.final_project.exceptions.CopiesNotFoundException;
import mateuszteam.final_project.exceptions.CopyNotFoundException;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @ExceptionHandler(CopyNotFoundException.class)
    ErrorResponse handleCopyNotFoundException(final CopyNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CopiesNotFoundException.class)
    NotEnoughFreeCopiesErrorResponse handleCopiesNotFoundException(final CopiesNotFoundException exception) {
        return new NotEnoughFreeCopiesErrorResponse(exception.getMessage(), exception.getMoviesWithoutFreeCopies());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    ErrorResponse handleUsernameNotFoundException(final UsernameNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }



}
