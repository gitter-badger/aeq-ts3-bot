package de.esports.aeq.ts3bot.core.api;

public class BotConfiguration {

    private String hostname;
    private int queryPort;
    private int virtualServerPort = 0;
    private String username;
    private String password;
    private String name;

    public BotConfiguration(String hostname, int queryPort, int virtualServerPort, String username, String password,
                            String name) {
        this.hostname = hostname;
        this.queryPort = queryPort;
        this.virtualServerPort = virtualServerPort;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getQueryPort() {
        return queryPort;
    }

    public void setQueryPort(int queryPort) {
        this.queryPort = queryPort;
    }

    public int getVirtualServerPort() {
        return virtualServerPort;
    }

    public void setVirtualServerPort(int virtualServerPort) {
        this.virtualServerPort = virtualServerPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}