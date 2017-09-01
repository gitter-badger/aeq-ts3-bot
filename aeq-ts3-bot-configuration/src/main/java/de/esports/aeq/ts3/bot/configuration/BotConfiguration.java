/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration", namespace = "http://www.w3schools.com")
public class BotConfiguration {

    private String name;
    private Server server;
    private Permissions permissions;

    public BotConfiguration() {

    }

    @XmlElement(name = "name", namespace = "http://www.w3schools.com")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "server", namespace = "http://www.w3schools.com")
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @XmlElement(name = "permissions", namespace = "http://www.w3schools.com")
    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static class Server {

        private String hostname;
        private int queryPort;
        private int virtualServerPort = 0;

        public Server() {

        }

        @XmlElement(name = "hostname", namespace = "http://www.w3schools.com")
        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        @XmlElement(name = "query-port", namespace = "http://www.w3schools.com")
        public int getQueryPort() {
            return queryPort;
        }

        public void setQueryPort(int queryPort) {
            this.queryPort = queryPort;
        }

        @XmlElement(name = "virtual-server-port", namespace = "http://www.w3schools.com")
        public int getVirtualServerPort() {
            return virtualServerPort;
        }

        public void setVirtualServerPort(int virtualServerPort) {
            this.virtualServerPort = virtualServerPort;
        }
    }

    public static class Permissions {

        private String username;
        private String password;

        public Permissions() {

        }

        @XmlElement(name = "query-user", namespace = "http://www.w3schools.com")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @XmlElement(name = "query-key", namespace = "http://www.w3schools.com")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}