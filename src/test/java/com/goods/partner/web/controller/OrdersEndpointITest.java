package com.goods.partner.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.goods.partner.AbstractBaseITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
public class OrdersEndpointITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    void givenOrders_whenCalculateOrders_thenJsonReturned() throws Exception {

        mockMvc.perform(get("/calculate/orders")
                        .param("date", "2022-07-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                """
                                        {\"date\":\"2022-07-10\",\"orders\":

                                        [{\"orderId\":2,\"orderNumber\":43532,\"createdDate\":\"2022-07-09\"
                                        ,\"orderData\":
                                        {\"clientName\":\"ТОВ Пекарня\",\"address\":\"м. Київ, вул. Металістів, 8, оф. 4-24\"
                                        ,\"managerFullName\":\"Іван Шугай\",\"products\":
                                        [{\"productName\":\"6798 Фарба харчова червона\",\"amount\":3,\"storeName\":\"Склад №1\"}
                                        ,{\"productName\":\"576853 Масло екстра\",\"amount\":4,\"storeName\":\"Склад №1\"}]}}

                                        ,{\"orderId\":3,\"orderNumber\":45463,\"createdDate\":\"2022-07-09\"
                                        ,\"orderData\":
                                        {\"clientName\":\"ТОВ Кондитерська\",\"address\":\"м. Київ, вул. Хрещатик, 1\"
                                        ,\"managerFullName\":\"Андрій Бублик\",\"products\":
                                        [{\"productName\":\"66784 Арахісова паста\",\"amount\":5,\"storeName\":\"Склад №1\"}
                                        ,{\"productName\":\"8795 Мука екстра\",\"amount\":5,\"storeName\":\"Склад №2\"}]}}]}
                                        """));
    }

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    void givenNoOrdersForSpecifiedDate_whenCalculateOrders_thenJsonWithEmptyOrdersFieldReturned() throws Exception {

        mockMvc.perform(get("/calculate/orders")
                        .param("date", "2000-01-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"date\":\"2000-01-01\",\"orders\":[]}"));
    }
}