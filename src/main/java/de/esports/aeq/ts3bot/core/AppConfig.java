package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.config.ConfigurationBuilder;
import de.esports.aeq.ts3bot.config.StaticConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "de.esports.aeq.ts3bot")
public class AppConfig {

    @Bean
    public ConfigurationBuilder configurationBuilder() {
        return new StaticConfigurationBuilder();
    }

}
