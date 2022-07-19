package com.goods.partner.sqltracker;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.goods.partner.service.impl.OrderServiceImpl;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import javax.sql.DataSource;
import java.time.LocalDate;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@SpringBootTest(classes = JpaTestConfiguration.class)
@DBRider
@ActiveProfiles("test")
@Testcontainers
//@DatabaseSetup("datasets/common/dataset.yml")
public class SqlTrackerTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:alpine")
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


    @Test
    @DataSet(value = "common/dataset.yml",
            disableConstraints = true)
    public void validateQueries() {
        SQLStatementCountValidator.reset();

        orderService.calculateOrders(LocalDate.of(2022, 07, 12));
        orderService.calculateRoutes(LocalDate.of(2022, 07, 12));
        orderService.calculateStores(LocalDate.of(2022, 07, 12));

        assertSelectCount(1);
    }
}
