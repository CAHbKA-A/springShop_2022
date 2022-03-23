package ru.gb.springShop.auth;
/*тест жексона. в целом безполезный тест*/

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.gb.springShop.auth.entities.Role;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jackson; //тестер

    @Test
    public void SerialisationToJsonTest() throws Exception {
        //подготоавливаем объект\

        Role role = new Role();
        role.setName("Юзер_в_кирилице");
        role.setId(123L);
        assertThat(jackson.write(role))//обьект -> json
                .hasJsonPathNumberValue("$.id") //начинаем проверять, что он наворотил. 1. выдергиваем полк id, проверяем что это цифра
                .extractingJsonPathStringValue("$.name").isEqualTo("Юзер_в_кирилице"); // 2.выдергиваем поле name, проверяем, что это string. и сравниваем то , что лежит с тем, что туда поклали
    }

    @Test
    public void DeserialisationFromJsonTest() throws Exception {
        //формируем json объект
        String jsonText = "{\"id\": 1231,\"name\":\"Юзер_Name\"}";

        //будем сравнивать с обьектом :
        Role realRole = new Role();
        realRole.setId(1231L);
        realRole.setName("Юзер_Name");

        //распарисваем и сравниваем поля
        assertThat(jackson.parse(jsonText)).isEqualTo(realRole);
        assertThat(jackson.parseObject(jsonText).getName()).isEqualTo(realRole.getName());
    }


}