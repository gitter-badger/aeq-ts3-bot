package de.esports.aeq.ts3bot.event;

public class GuestsClientJoinHandlerConfiguration {

    /**
     * Total amount of messages send, one for each time the user reconnects.
     */
    private int repeatAmount = -1;

    /**
     * If there is a timeout the user wont be notified within that time.
     */
    private int repeatTimeout = -1;

    /**
     * Create a new configuration with default values.
     */
    public GuestsClientJoinHandlerConfiguration() {

    }

    public GuestsClientJoinHandlerConfiguration(int repeatAmount, int repeatTimeout) {
        this.repeatAmount = repeatAmount;
        this.repeatTimeout = repeatTimeout;
    }

    public int getRepeatAmount() {
        return repeatAmount;
    }

    public void setRepeatAmount(int repeatAmount) {
        this.repeatAmount = repeatAmount;
    }

    public int getRepeatTimeout() {
        return repeatTimeout;
    }

    public void setRepeatTimeout(int repeatTimeout) {
        this.repeatTimeout = repeatTimeout;
    }
}
