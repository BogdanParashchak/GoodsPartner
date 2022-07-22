package com.goods.partner.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.goods.partner.AbstractBaseITest;
import org.junit.jupiter.api.DisplayName;
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
public class StoresEndpointITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    @DisplayName("Check Json returned after calculate stores")
    void givenStores_whenCalculateStores_thenJsonReturned() throws Exception {

        mockMvc.perform(get("/calculate/stores")
                        .param("date", "2022-07-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                """
                                        {\"date\":\"2022-07-12\",\"stores\":
                                        [{\"storeId\":1,\"storeName\":\"Склад №1\",\"orders\":
                                        [{\"orderId\":6,\"orderNumber\":356325,\"totalOrderWeight\":59.32}]}]}
                                        """));
    }

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    @DisplayName("Check empty Json returned after calculate stores")
    void givenNoStoresForSpecifiedDate_whenCalculateStores_thenJsonWithEmptyStoresFieldReturned() throws Exception {

        mockMvc.perform(get("/calculate/stores")
                        .param("date", "2002-07-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"date\":\"2002-07-12\",\"stores\":[]}"));
    }
}
