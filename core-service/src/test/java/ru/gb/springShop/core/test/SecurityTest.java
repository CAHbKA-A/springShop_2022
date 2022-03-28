package ru.gb.springShop.core.test;

/*проверка досупа к закрытым и открытым эндпоинтам, а так же парсин токена*/
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test //открытый эндпоинт
    public void securityAccessAllowedTest() throws Exception {
        mockMvc.perform(get("/api/v1/products"))  // ломимся в открытый эндпоинт
                .andDo(print())  //распечатали
                .andExpect(status().isOk())//открытый = должны получить статус ок
                .andExpect(jsonPath("$").isArray()); //проверяем, что прилетел лист
    }
//todo поправить запрос
    @Test//(401). тест закрытого эндпоинта. пока тест не проходит, потому что этот эндпоинт не закрыт и вообще кривой запрос.
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test// ошибка 400/ бесполезный тест. просто для пробы
    public void BedRequestTest() throws Exception {
        mockMvc.perform(post("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    // todo разобратсья с WithMockUser
//    @Test
//    @WithMockUser(username = "Bob", roles = "ADMIN")
//    public void securityCheckUserTest() throws Exception {
//        mockMvc.perform(get("/api/v1/orders"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }


}
