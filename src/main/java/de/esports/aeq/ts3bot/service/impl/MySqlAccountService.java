package de.esports.aeq.ts3bot.service.impl;

import de.esports.aeq.ts3bot.service.api.AccountService;
import io.reactivex.Observable;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlAccountService implements AccountService {

    @Override
    public Observable<String> getAccessKey(String ts3Id) {
        return null;
    }

    @Override
    public Observable<Boolean> linkAccessKey(String ts3Id, String key) {
        return null;
    }

}
