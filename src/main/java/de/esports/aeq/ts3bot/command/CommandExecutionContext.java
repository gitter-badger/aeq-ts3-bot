package de.esports.aeq.ts3bot.command;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public class CommandExecutionContext {

    private TS3Api api;
    private TS3ApiAsync apiAsync;
    private TextMessageEvent messageEvent;

    public CommandExecutionContext(TS3Api api, TS3ApiAsync apiAsync, TextMessageEvent messageEvent) {
        this.api = api;
        this.apiAsync = apiAsync;
        this.messageEvent = messageEvent;
    }

    public TS3Api getApi() {
        return api;
    }

    public void setApi(TS3Api api) {
        this.api = api;
    }

    public TS3ApiAsync getApiAsync() {
        return apiAsync;
    }

    public void setApiAsync(TS3ApiAsync apiAsync) {
        this.apiAsync = apiAsync;
    }

    public TextMessageEvent getMessageEvent() {
        return messageEvent;
    }

    public void setMessageEvent(TextMessageEvent messageEvent) {
        this.messageEvent = messageEvent;
    }
}
