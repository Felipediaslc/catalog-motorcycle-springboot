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

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleInvalidRequestBody(MethodArgumentNotValidException exception) {

        HashMap<String, Object> body = new HashMap<>();

        List<ValidateFieldResponseDto> errorsDto = new ArrayList<>();
        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("fieldsValidations", errorsDto);

        fieldErros.forEach(e -> {
            String fieldMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidateFieldResponseDto validationErrorDto = new ValidateFieldResponseDto(fieldMessage, e.getField());
            errorsDto.add(validationErrorDto);
        });

        return ResponseEntity.ok(body);
    }

}
