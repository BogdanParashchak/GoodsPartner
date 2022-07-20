package com.goods.partner.sqltracker;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.goods.partner.AbstractBaseTest;
import com.goods.partner.service.impl.OrderServiceImpl;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
<<<<<<< HEAD
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
>>>>>>> b429908ce4f747fb07f0071b4ab8a94634afc858

import java.time.LocalDate;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@SpringBootTest(classes = TestConfiguration.class)
@DBRider
@ActiveProfiles("test")
public class SqlTrackerTest extends AbstractBaseTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    public void validateQueries() {
        SQLStatementCountValidator.reset();

        orderService.calculateOrders(LocalDate.of(2022, 07, 12));
        orderService.calculateRoutes(LocalDate.of(2022, 07, 12));
        orderService.calculateStores(LocalDate.of(2022, 07, 12));

        assertSelectCount(12);
    }
}
