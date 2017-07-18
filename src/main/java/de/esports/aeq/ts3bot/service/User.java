package de.esports.aeq.ts3bot.service;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class User {

    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
