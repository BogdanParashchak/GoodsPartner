package com.goods.partner.orderservice;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.goods.partner.AbstractBaseITest;
import com.goods.partner.service.impl.DefaultOrderService;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@Import(TestConfigurationToCountAllQueries.class)
@DBRider
public class DefaultOrderServiceITest extends AbstractBaseITest {

    @Autowired
    private DefaultOrderService orderService;

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    @DisplayName("Check queries quantity correct after calculate orders")
    void validateQueriesAfterCalculateOrders() {
        SQLStatementCountValidator.reset();

        orderService.calculateOrders(LocalDate.of(2022, 07, 12));

        assertSelectCount(5);
    }

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    @DisplayName("Check queries quantity correct after calculate stores")
    void validateQueriesAfterCalculateStores() {
        SQLStatementCountValidator.reset();

        orderService.calculateStores(LocalDate.of(2022, 07, 12));

        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    @DisplayName("Check queries quantity correct after calculate routes")
    void validateQueriesAfterCalculateRoutes() {
        SQLStatementCountValidator.reset();

        orderService.calculateRoutes(LocalDate.of(2022, 07, 12));

        assertSelectCount(6);
    }
}
