package br.com.catalog.handler;

import br.com.catalog.dto.validation.ValidateFieldResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class RequestBodyExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleInvalidRequestBody(MethodArgumentNotValidException exception) {
        List<ValidateFieldResponseDto> errorsDto = new ArrayList<>();
        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        HashMap<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("typeStatus", HttpStatus.BAD_REQUEST);
        body.put("typeError", "fieldsValidations");
        body.put("messages", errorsDto);

        fieldErros.forEach(e -> {
            String fieldMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidateFieldResponseDto validationErrorDto = new ValidateFieldResponseDto(fieldMessage, e.getField());
            errorsDto.add(validationErrorDto);
        });

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

}
