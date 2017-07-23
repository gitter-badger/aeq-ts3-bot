package de.esports.aeq.ts3bot.core.api;

/**
 * Created by Lukas on 23.07.2017.
 */
public class TS3EventType {

    /**
     * Server properties was edited
     */
    public static final String NOTIFY_SERVER_EDITED = "notifyserveredited";

    /**
     * Client join server
     */
    public static final String NOTIFY_CLIENT_ENTER_VIEW = "notifycliententerview";

    /**
     * Client left server
     */
    public static final String NOTIFY_CLIENT_LEFT_VIEW = "notifyclientleftview";

    /**
     * Client was moved or switched channel
     */
    public static final String NOTIFY_CLIENT_MOVED = "notifyclientmoved";

    /**
     * Channel was created
     */
    public static final String NOTIFY_CHANNEL_CREATED = "notifychannelcreated";

    /**
     * Channel properties was edited
     */
    public static final String NOTIFY_CHANNEL_EDITED = "notifychanneledited";

    /**
     * Channel was deleted
     */
    public static final String NOTIFY_CHANNEL_DELETED = "notifychanneldeleted";

}
