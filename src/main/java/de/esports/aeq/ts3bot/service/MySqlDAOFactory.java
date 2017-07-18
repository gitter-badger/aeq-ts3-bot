package de.esports.aeq.ts3bot.service;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlDAOFactory implements DAOFactory {

    @Override
    public AccountDAO getAccountDAO() {
        return new MySqlAccountDAO();
    }

    @Override
    public ApplicationDAO getApplicationDAO() {
        return new MySqlApplicationDAO();
    }
}
