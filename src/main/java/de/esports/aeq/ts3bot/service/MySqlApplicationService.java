package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.ApplicationService;
import io.reactivex.Observable;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlApplicationService implements ApplicationService {


    @Override
    public Observable<Boolean> acceptApplication(String ts3id) {
        return null;
    }

    @Override
    public Observable<Boolean> rejectApplication(String ts3id) {
        return null;
    }
}
