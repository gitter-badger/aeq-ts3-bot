package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.Credentials;

import java.io.Console;
import java.util.Scanner;

public class CredentialsUtil {

    /**
     * Attempts to read {@link Credentials} from the command line.
     * <p>
     * This method does block the current thread until a result is present.
     *
     * @return the {@link Credentials}, not null
     * @throws Exception if an exception occurred during the process
     */
    public static Credentials readFromCommandLine() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = null;
        while (username == null || username.isEmpty()) {
            if (username != null) {
                System.out.println("Username cannot be empty.");
            }
            username = scanner.next();
        }
        System.out.print("Password: ");
        String password = null;
        while (password == null || password.isEmpty()) {
            if (password != null) {
                System.out.println("Password cannot be empty.");
            }
            password = scanner.next();
        }
        return new Credentials(username, password);
    }

}
