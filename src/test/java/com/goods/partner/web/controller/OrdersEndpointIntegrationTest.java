package com.goods.partner.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@DBRider
@ActiveProfiles("test")
@AutoConfigureMockMvc
class OrdersEndpointIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

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
                                "{\"date\":\"2022-07-10\",\"orders\":" +

                                "[{\"orderId\":2,\"orderNumber\":43532,\"createdDate\":\"2022-07-09\"" +
                                ",\"orderData\":" +
                                "{\"clientName\":\"?????? ??????????????\",\"address\":\"??. ????????, ??????. ????????????????????, 8, ????. 4-24\"" +
                                ",\"managerFullName\":\"???????? ??????????\",\"products\":" +
                                "[{\"productName\":\"6798 ?????????? ?????????????? ??????????????\",\"amount\":3,\"storeName\":\"?????????? ???1\"}" +
                                ",{\"productName\":\"576853 ?????????? ????????????\",\"amount\":4,\"storeName\":\"?????????? ???1\"}]}}" +

                                ",{\"orderId\":3,\"orderNumber\":45463,\"createdDate\":\"2022-07-09\"" +
                                ",\"orderData\":" +
                                "{\"clientName\":\"?????? ????????????????????????\",\"address\":\"??. ????????, ??????. ????????????????, 1\"" +
                                ",\"managerFullName\":\"???????????? ????????????\",\"products\":" +
                                "[{\"productName\":\"66784 ?????????????????? ??????????\",\"amount\":5,\"storeName\":\"?????????? ???1\"}" +
                                ",{\"productName\":\"8795 ???????? ????????????\",\"amount\":5,\"storeName\":\"?????????? ???2\"}]}}]}"));
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