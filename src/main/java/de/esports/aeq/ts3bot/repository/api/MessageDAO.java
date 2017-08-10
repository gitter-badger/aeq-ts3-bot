package de.esports.aeq.ts3bot.repository.api;

import de.esports.aeq.ts3bot.message.Message;

import java.util.List;

public interface MessageDAO {

    void createMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(int messageId);

    List<Message> getMessages(String context, String id, String locale);
}
