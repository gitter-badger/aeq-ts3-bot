/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.core.NotImplemented;
import de.esports.aeq.ts3bot.message.Message;
import de.esports.aeq.ts3bot.repository.api.MessageDAO;
import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Repository
public class JdbcMessageDAO implements MessageDAO {

    private static final Logger log = LoggerFactory.getLogger(JdbcMessageDAO.class);

    private final DataSource dataSource;

    @Autowired
    public JdbcMessageDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @NotImplemented
    public void createMessage(Message message) {
        // TODO(glains)
        String query = "";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.close();

        } catch (SQLException e) {
            log.warn("could not create message", e);
        } finally {
            if (conn != null) {
                DbUtils.closeQuietly(conn);
            }
        }
    }

    @Override
    @NotImplemented
    public void updateMessage(Message message) {
        // TODO(glains)
        String query = "";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.close();

        } catch (SQLException e) {
            log.warn("could not update message", e);
        } finally {
            if (conn != null) {
                DbUtils.closeQuietly(conn);
            }
        }
    }

    @Override
    @NotImplemented
    public void deleteMessage(int messageId) {
        // TODO(glains)
        String query = "";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.close();

        } catch (SQLException e) {
            log.warn("could not delete message", e);
        } finally {
            if (conn != null) {
                DbUtils.closeQuietly(conn);
            }
        }
    }

    @Override
    @NotImplemented
    public List<Message> getMessages(String id, String locale) {
        // TODO(glains)
        String query = "SELECT * FROM messages WHERE 1=1";
        HashMap<String, String> params = new HashMap<>();
        if (id != null) {
            query += " AND id = ?";
            params.put("id", id);
        }
        if (locale != null) {
            query += " AND query = ?";
            params.put("locale", locale);
        }
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);

            statement.close();

        } catch (SQLException e) {
            log.warn("could not get messages", e);
        } finally {
            if (conn != null) {
                DbUtils.closeQuietly(conn);
            }
        }
        return Collections.emptyList();
    }

}
