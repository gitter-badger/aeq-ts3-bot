package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;

public abstract class TS3EventHandler<T> implements EventHandler<T> {

    protected TS3Api api;
    protected TS3ApiAsync apiAsync;

    public TS3EventHandler(TS3Api api, TS3ApiAsync apiAsync) {
        this.api = api;
        this.apiAsync = apiAsync;
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
}
