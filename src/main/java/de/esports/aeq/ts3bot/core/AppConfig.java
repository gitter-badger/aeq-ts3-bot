package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.config.ConfigurationBuilder;
import de.esports.aeq.ts3bot.config.StaticConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "de.esports.aeq.ts3bot")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        // TODO(glains): load from property file
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public ConfigurationBuilder configurationBuilder() {
        return new StaticConfigurationBuilder();
    }

}
