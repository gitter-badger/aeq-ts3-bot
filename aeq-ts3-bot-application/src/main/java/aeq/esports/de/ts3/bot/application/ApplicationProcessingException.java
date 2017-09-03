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

package aeq.esports.de.ts3.bot.application;

import aeq.esports.de.ts3.bot.application.api.ApplicationWorkflowEngine;
import de.esports.aeq.ts3.bot.model.Application;

/**
 * Exception thrown when an {@link Application} cannot be processed by the {@link ApplicationWorkflowEngine}.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 03.09.2017
 */
public class ApplicationProcessingException extends Exception {

    public ApplicationProcessingException() {
        super();
    }

    public ApplicationProcessingException(String message) {
        super(message);
    }

    public ApplicationProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationProcessingException(Throwable cause) {
        super(cause);
    }

    protected ApplicationProcessingException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
