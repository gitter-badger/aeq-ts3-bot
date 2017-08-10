/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.Credentials;

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
