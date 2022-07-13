package com.goods.partner.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
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
public class RoutesControllerIntegrationTest {
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
    @DisplayName("given Routes when Calculate Routers then Correct Json Returned")
    @DataSet(value = "common/dataset_routes.yml",
            disableConstraints = true)
    void givenRoutes_whenCalculateRouters_thenCorrectJsonReturned() throws Exception {

        mockMvc.perform(get("/calculate/routes")
                        .param("date", "2022-07-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{" +
                                "  \"date\": \"2022-07-12\"," +
                                "  \"routes\": [" +
                                "    {" +
                                "      \"routeId\": 1," +
                                "      \"status\": \"new\"," +
                                "      \"totalWeight\": 100.0," +
                                "      \"totalPoints\": 5," +
                                "      \"totalOrders\": 10," +
                                "      \"distance\": 50.0," +
                                "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"routeLink\": \"http//\"," +
                                "      \"storeName\": \"storeName1\"," +
                                "      \"storeAddress\": \"Київ\"," +
                                "      \"clients\": [" +
                                "        {" +
                                "          \"clientId\": 2," +
                                "          \"clientName\": \"ТОВ Кондитерська\"," +
                                "          \"addresses\": [" +
                                "            {" +
                                "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                "              \"orders\": [" +
                                "                {" +
                                "                  \"orderId\": 6," +
                                "                  \"orderNumber\": 356325," +
                                "                  \"orderTotalWeight\": 59.32000000000001" +
                                "                }" +
                                "              ]," +
                                "              \"addressTotalWeight\": 59.32000000000001" +
                                "            }" +
                                "          ]" +
                                "        }" +
                                "      ]" +
                                "    }," +
                                "    {" +
                                "      \"routeId\": 2," +
                                "      \"status\": \"in progress\"," +
                                "      \"totalWeight\": 200.0," +
                                "      \"totalPoints\": 5," +
                                "      \"totalOrders\": 10," +
                                "      \"distance\": 50.0," +
                                "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"routeLink\": \"http//\"," +
                                "      \"storeName\": \"storeName2\"," +
                                "      \"storeAddress\": \"Фастів\"," +
                                "      \"clients\": [" +
                                "      {" +
                                "          \"clientId\": 2," +
                                "          \"clientName\": \"ТОВ Кондитерська\"," +
                                "          \"addresses\": [" +
                                "            {" +
                                "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                "              \"orders\": [" +
                                "                {" +
                                "                  \"orderId\": 6," +
                                "                  \"orderNumber\": 356325," +
                                "                  \"orderTotalWeight\": 59.32000000000001" +
                                "                }" +
                                "              ]," +
                                "              \"addressTotalWeight\": 59.32000000000001" +
                                "            }" +
                                "          ]" +
                                "        }" +
                                "      ]" +
                                "    }," +
                                "    {" +
                                "      \"routeId\": 3," +
                                "      \"status\": \"finished\"," +
                                "      \"totalWeight\": 300.0," +
                                "      \"totalPoints\": 5," +
                                "      \"totalOrders\": 10," +
                                "      \"distance\": 50.0," +
                                "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                "      \"routeLink\": \"http//\"," +
                                "      \"storeName\": \"storeName3\"," +
                                "      \"storeAddress\": \"Одеса\"," +
                                "      \"clients\": [" +
                                "        {" +
                                "          \"clientId\": 2," +
                                "          \"clientName\": \"ТОВ Кондитерська\"," +
                                "          \"addresses\": [" +
                                "            {" +
                                "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                "              \"orders\": [" +
                                "                {" +
                                "                  \"orderId\": 6," +
                                "                  \"orderNumber\": 356325," +
                                "                  \"orderTotalWeight\": 59.32000000000001" +
                                "                }" +
                                "              ]," +
                                "              \"addressTotalWeight\": 59.32000000000001" +
                                "            }" +
                                "          ]" +
                                "        }" +
                                "      ]" +
                                "    }" +
                                "  ]" +
                                "}"));
    }

    @Test
    @DisplayName("given Routes when Calculate Routers then Incorrect Json Returned")
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    void givenRoutes_whenCalculateRouters_thenIncorrectJsonReturned() throws Exception {
        mockMvc.perform(get("/calculate/routes")
                        .param("date", "2022-07-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))

                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                "{" +
                                        "  \"date\": \"2022-07-12\"," +
                                        "  \"routes\": [" +
                                        "    {" +
                                        "      \"routeId\": 1," +
                                        "      \"status\": \"new\"," +
                                        "      \"totalWeight\": 100.0," +
                                        "      \"totalPoints\": 5," +
                                        "      \"totalOrders\": 10," +
                                        "      \"distance\": 50.0," +
                                        "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"routeLink\": \"http//\"," +
                                        "      \"storeName\": \"storeName1\"," +
                                        "      \"storeAddress\": \"Київ\"," +
                                        "      \"clients\": [" +
                                        "        {" +
                                        "          \"clientId\": 2," +
                                        "          \"clientName\": \"ТОВ Кондитерська\"," +
                                        "          \"addresses\": [" +
                                        "            {" +
                                        "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                        "              \"orders\": [" +
                                        "                {" +
                                        "                  \"orderId\": 6," +
                                        "                  \"orderNumber\": 356325," +
                                        "                  \"orderTotalWeight\": 59.32000000000001" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 5," +
                                        "                  \"orderNumber\": 432565," +
                                        "                  \"orderTotalWeight\": 338.28" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 4," +
                                        "                  \"orderNumber\": 97342," +
                                        "                  \"orderTotalWeight\": 5.5" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 3," +
                                        "                  \"orderNumber\": 45463," +
                                        "                  \"orderTotalWeight\": 620.55" +
                                        "                }" +
                                        "              ]," +
                                        "              \"addressTotalWeight\": 1023.65" +
                                        "            }" +
                                        "          ]" +
                                        "        }" +
                                        "      ]" +
                                        "    }," +
                                        "    {" +
                                        "      \"routeId\": 2," +
                                        "      \"status\": \"in progress\"," +
                                        "      \"totalWeight\": 200.0," +
                                        "      \"totalPoints\": 5," +
                                        "      \"totalOrders\": 10," +
                                        "      \"distance\": 50.0," +
                                        "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"routeLink\": \"http//\"," +
                                        "      \"storeName\": \"storeName2\"," +
                                        "      \"storeAddress\": \"Фастів\"," +
                                        "      \"clients\": [" +
                                        "          {" +
                                        "          \"clientId\": 2," +
                                        "          \"clientName\": \"ТОВ Кондитерська\"," +
                                        "          \"addresses\": [" +
                                        "            {" +
                                        "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                        "              \"orders\": [" +
                                        "                {" +
                                        "                  \"orderId\": 6," +
                                        "                  \"orderNumber\": 356325," +
                                        "                  \"orderTotalWeight\": 59.32000000000001" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 5," +
                                        "                  \"orderNumber\": 432565," +
                                        "                  \"orderTotalWeight\": 338.28" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 4," +
                                        "                  \"orderNumber\": 97342," +
                                        "                  \"orderTotalWeight\": 5.5" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 3," +
                                        "                  \"orderNumber\": 45463," +
                                        "                  \"orderTotalWeight\": 620.55" +
                                        "                }" +
                                        "              ]," +
                                        "              \"addressTotalWeight\": 1023.65" +
                                        "            }" +
                                        "          ]" +
                                        "        }" +
                                        "      ]" +
                                        "    }," +
                                        "    {" +
                                        "      \"routeId\": 3," +
                                        "      \"status\": \"finished\"," +
                                        "      \"totalWeight\": 300.0," +
                                        "      \"totalPoints\": 5," +
                                        "      \"totalOrders\": 10," +
                                        "      \"distance\": 50.0," +
                                        "      \"estimatedTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"startTime\": \"+999999999-12-31T23:59:59.999999999\"," +
                                        "      \"finishTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"spentTime\": \"-999999999-01-01T00:00:00\"," +
                                        "      \"routeLink\": \"http//\"," +
                                        "      \"storeName\": \"storeName3\"," +
                                        "      \"storeAddress\": \"Одеса\"," +
                                        "      \"clients\": [" +
                                        "          {" +
                                        "          \"clientId\": 2," +
                                        "          \"clientName\": \"ТОВ Кондитерська\"," +
                                        "          \"addresses\": [" +
                                        "            {" +
                                        "              \"address\": \"м. Київ, вул. Хрещатик, 1\"," +
                                        "              \"orders\": [" +
                                        "                {" +
                                        "                  \"orderId\": 6," +
                                        "                  \"orderNumber\": 356325," +
                                        "                  \"orderTotalWeight\": 59.32000000000001" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 5," +
                                        "                  \"orderNumber\": 432565," +
                                        "                  \"orderTotalWeight\": 338.28" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 4," +
                                        "                  \"orderNumber\": 97342," +
                                        "                  \"orderTotalWeight\": 5.5" +
                                        "                }," +
                                        "                {" +
                                        "                  \"orderId\": 3," +
                                        "                  \"orderNumber\": 45463," +
                                        "                  \"orderTotalWeight\": 620.55" +
                                        "                }" +
                                        "              ]," +
                                        "              \"addressTotalWeight\": 1023.65" +
                                        "            }" +
                                        "          ]" +
                                        "        }" +
                                        "      ]" +
                                        "    }" +
                                        "  ]" +
                                        "}"));
    }
}
