package ru.gb.springShop.exceptions;
/*искючение попроще, для обработкт кривых запросов и ошибок */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
