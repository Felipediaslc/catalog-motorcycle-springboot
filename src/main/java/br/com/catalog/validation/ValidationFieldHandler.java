package br.com.catalog.validation;

import br.com.catalog.dto.validation.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationFieldHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorDto> handle(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        List<ValidationErrorDto> errorsDto = new ArrayList<>();

        errors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            String field = e.getField();

            ValidationErrorDto dto = new ValidationErrorDto(message, field);
            errorsDto.add(dto);
        });

        return errorsDto;
    }

}
