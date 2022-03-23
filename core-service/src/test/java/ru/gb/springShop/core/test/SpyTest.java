package ru.gb.springShop.core.test;
/*статистика*/


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo разобраться. пока просто закопипастил
@SpringBootTest
public class SpyTest {
    @Spy //что отслеживать
    private List<String> spiedList = new ArrayList<>();

    @Test
    public void spyTest() {
        spiedList.add("Э");
        spiedList.add("5");
        spiedList.add("@");

        //проверка, что вызывались методы с этими праметрами
        Mockito.verify(spiedList).add("Э");
        Mockito.verify(spiedList).add("5");
        Mockito.verify(spiedList).add("@");

        assertEquals(3, spiedList.size()); //сколько раз добавился

        Mockito.doReturn(100).when(spiedList).size(); //подмена при вызове метода

        assertEquals(100, spiedList.size()); // после подмены

        System.out.println(spiedList.getClass().getName());
    }
}
