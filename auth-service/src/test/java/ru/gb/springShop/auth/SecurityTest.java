package ru.gb.springShop.auth;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void securityTokenTest() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"bob\",\n" +
                "\t\"password\": \"100\"\n" +
                "}";
        MvcResult result = mockMvc.perform(
                        post("/auth/")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

//        String token = result.getResponse().getContentAsString();
//        token = token.replace("{\"token\":\"", "").replace("\"}", "");
//
//        mockMvc.perform(get("/api/v1/orders")
//                        .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
    }
    @Test// ошибка 400
    public void BedRequestTest() throws Exception {
        mockMvc.perform(get("/auth_check"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
