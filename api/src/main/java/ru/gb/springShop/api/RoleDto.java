package ru.gb.springShop.api;


import io.swagger.v3.oas.annotations.media.Schema;

public class RoleDto {
    @Schema(description = "Роль пользователя", example = "Role_meat")
    private String value;

    public RoleDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
