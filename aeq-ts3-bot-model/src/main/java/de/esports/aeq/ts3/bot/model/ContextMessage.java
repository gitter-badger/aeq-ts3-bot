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

package de.esports.aeq.ts3.bot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

/**
 * Represents a context message.
 * <p>
 * Unlike {@link java.util.ResourceBundle}, each message is not (uniquely) identified by its key, but rather with a
 * context to allow multiple messages per context. This may be used to vary between different messages within a single
 * context.
 * <p>
 * Also, using the {@link Locale} of the message, it may be provided in different languages.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 25.08.2017
 */
@Entity
@Table(name = "message")
public class ContextMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The context to summarize messages of the same type.
     */
    @Column(name = "context")
    private String context;

    /**
     * The locale of the message.
     */
    @Column(name = "locale")
    private Locale locale;

    /**
     * The actual message using the specified {@link Locale}.
     */
    @Column(name = "message")
    private String message;

    /**
     * Default constructor.
     */
    public ContextMessage() {
        // no argument constructor
    }

    /**
     * Constructs a new {@link ContextMessage}.
     *
     * @param context the context
     * @param locale  the {@link Locale}
     * @param message the actual message
     */
    public ContextMessage(String context, Locale locale, String message) {
        this.context = context;
        this.locale = locale;
        this.message = message;
    }

    /**
     * @return the id of this message
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id of this message
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the context of this message
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context the context of this message
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return the {@link Locale} of this message
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the {@link Locale} of this message
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the message of this message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message of this message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}