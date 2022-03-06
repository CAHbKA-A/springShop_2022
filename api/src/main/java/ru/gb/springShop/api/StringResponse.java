package ru.gb.springShop.api;


/*объект который будет возвращаться на фронт, при запросу статуса авторизации. содержит только строку с именем*/

public class StringResponse {
    private String value;

    public StringResponse(String value) {
        this.value = value;
    }

    public StringResponse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
