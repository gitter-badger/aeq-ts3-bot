package de.esports.aeq.ts3bot.service;

/**
 * Created by Lukas on 19.07.2017.
 */
public abstract class DAOFactory {

    public static final int MYSQL = 1;

    public abstract AccountDAO getAccountDAO();

    public abstract ApplicationDAO getApplicationDAO();

    public static DAOFactory getDAOFactory(int factoryType) {
        switch (factoryType) {
            case MYSQL:
                return new MySqlDAOFactory();
            default:
                return null;
        }
    }

}
