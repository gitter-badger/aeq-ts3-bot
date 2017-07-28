package de.esports.aeq.ts3bot.util;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 27.07.2017.
 */
public class Args {

    private Args() {

    }

    public static void checkForNull(@Nullable Object object) {
        if (object == null)
            throw new IllegalArgumentException();
    }

    public static void checkForNull(@Nullable Object object, @Nullable String error) {
        if (object == null)
            throw new IllegalArgumentException(error);
    }

}
