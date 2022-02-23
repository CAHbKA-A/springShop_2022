package ru.gb.springShop.exceptions;

/*Иноформация о кртивых запросах */
public class AppError {
    //короткий код
    private int statusCode;
    //сообщение в чем именно проблема
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
