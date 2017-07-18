package de.esports.aeq.ts3bot.core;

import de.stefan1200.jts3servermod.interfaces.HandleBotEvents;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;
import org.jetbrains.annotations.Nullable;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class HandleBotEventsImpl implements HandleBotEvents {

    private static JTS3ServerMod_Interface jts3ServerMod;
    private static JTS3ServerQuery jts3ServerQuery;
    private static String prefix;

    @Nullable
    public static JTS3ServerMod_Interface getJts3ServerMod() {
        return jts3ServerMod;
    }

    @Nullable
    public static JTS3ServerQuery getJts3ServerQuery() {
        return jts3ServerQuery;
    }

    @Nullable
    public static String getPrefix() {
        return prefix;
    }

    @Override
    public void initClass(JTS3ServerMod_Interface modClass, JTS3ServerQuery queryLib, String prefix) {
        HandleBotEventsImpl.jts3ServerMod = modClass;
        HandleBotEventsImpl.jts3ServerQuery = queryLib;
        HandleBotEventsImpl.prefix = prefix;
    }

    @Override
    public void handleOnBotConnect() {

    }

    @Override
    public void handleAfterCacheUpdate() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void unload() {

    }

    @Override
    public boolean multipleInstances() {
        return false;
    }

    @Override
    public int getAPIBuild() {
        return 0;
    }

    @Override
    public String getCopyright() {
        return null;
    }
}
