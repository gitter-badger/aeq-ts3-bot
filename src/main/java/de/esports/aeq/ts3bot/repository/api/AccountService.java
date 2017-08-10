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

package de.esports.aeq.ts3bot.repository.api;

import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a general interface for account repository calls.
 *
 * @author Lukas Kannenberg
 * @since 19.07.2017.
 */
public interface AccountService {

    /**
     * Returns the access-key for a specific teamspeak id if present.
     * <p>
     *
     * @param ts3Id the teamspeak id of the user
     * @return an {@link Observable} that resolves when the action finished or rejects when an error occurred
     * during the process. The resolved String may be null if no access-key if present for that id.
     */
    @NotNull
    Observable<String> getAccessKey(@Nullable String ts3Id);

    /**
     * @param ts3Id the teamspeak id of the user
     * @param key   the access-key of the user
     * @return
     */
    @NotNull
    Observable<Boolean> linkAccessKey(@Nullable String ts3Id, @Nullable String key);

}
