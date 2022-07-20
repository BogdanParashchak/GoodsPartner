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
