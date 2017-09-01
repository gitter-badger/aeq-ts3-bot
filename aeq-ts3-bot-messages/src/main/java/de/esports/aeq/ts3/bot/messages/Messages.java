/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.messages;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Lukas on 24.07.2017.
 */
public class Messages {

    private static final Logger log = LoggerFactory.getLogger(Messages.class);

    public static final Locale locale = Locale.GERMAN;

    // Messages codes used in the database
    public static final String WELCOME = "welcome";
    public static final String WELCOME_NEW = "welcome_new";

    // Messages codes used in resource bundles
    private static final String BUNDLE_GENERAL_MESSAGES = "general";

    public static final String UNKNOWN_COMMAND = "unknown_command";
    public static final String ERROR_NOT_IMPLEMENTED = "error_not_implemented";
    public static final String ERROR_INVALID_PERMISSIONS = "error_invalid_permissions";
    public static final String ERROR_COMMAND_EXCEPTION = "error_command_exception";

    /**
     * Returns a translated string of the BUNDLE_GENERAL_MESSAGES according to the selected {@link Locale}
     *
     * @param message the message identifier
     * @param args    any additional arguments
     * @return the translated string
     */
    public static @Nullable String getTranslatedString(String message, Object... args) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES, locale);
            if (bundle.containsKey(message)) {
                return bundle.getString(message);
            } else {
                log.debug("default message bundle does not contain key {}", message);
            }
        } catch (MissingResourceException e) {
            log.warn("could not find localized message bundle: {}", e);
        }
        log.debug("attempting to load default message bundle");
        try {
            bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES);
            return bundle.getString(message);
        } catch (MissingResourceException e) {
            log.error("could not load default message bundle", e);
            return null;
        }
    }

}
