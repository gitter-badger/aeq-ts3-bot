package de.esports.aeq.ts3bot.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Contains the entry point of the application.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        AeqTS3Bot ts3Bot = context.getBean(AeqTS3Bot.class);
        ts3Bot.start();
    }

}
