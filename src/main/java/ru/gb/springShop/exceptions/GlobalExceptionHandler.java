package ru.gb.springShop.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //эту логику надо распространить на все контроллеры
@Slf4j //для логгера
public class GlobalExceptionHandler {
    // создаем метод, перехватывающий исключения
    @ExceptionHandler //чтобы создался этот эксепшн хендлен и перехватывал исключения
    public ResponseEntity<AppError> exceptionHandler(ResourceNotFoundException e) {
        //если поймали исключение
        log.error(e.getMessage(), e); //логер идет в спринге с @Slf4j. подклчать отдбно не требуется!!
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
