package ru.gb.springShop.api;

import io.swagger.v3.oas.annotations.media.Schema;

/*Иноформация о кртивых запросах */
public class AppError {
    //короткий код
    @Schema(description = "Код ошибки", required = true, example = "201")
    private int statusCode;

    //сообщение в чем именно проблема
    @Schema(description = "Описание ошибки", required = true, example = "продукт не найден")
    private String message;


    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public AppError() {
    }

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
