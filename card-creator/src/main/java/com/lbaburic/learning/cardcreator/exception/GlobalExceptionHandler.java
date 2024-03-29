package com.lbaburic.learning.cardcreator.exception;

import com.lbaburic.learning.cardcreator.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(exception.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCardNotFoundException(
            CardNotFoundException exception,
            WebRequest webRequest
    ) {


        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCardAlreadyExistsException(
            CardAlreadyExistsException exception,
            WebRequest webRequest
    ) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(exception.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOibException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidOibException(
            InvalidOibException exception,
            WebRequest webRequest
    ) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(exception.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

}
