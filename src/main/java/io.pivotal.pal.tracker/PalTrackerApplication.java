package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {


    @Bean
    TimeEntryRepository timeEntryRepository() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(System.getenv("SPRING_DATASOURCE_URL"));
        return new JdbcTimeEntryRepository(mysqlDataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }


}
