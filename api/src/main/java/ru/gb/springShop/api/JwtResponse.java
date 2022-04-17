package ru.gb.springShop.api;


//json  . ответка бэка фронту . сам токен

public class JwtResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

  public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }
}
