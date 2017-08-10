package de.esports.aeq.ts3bot.core;

import java.lang.annotation.*;

/**
 * Marks the annotated element as not implemented.
 * Not implemented methods may not be (fully) functional and thus, should not be used in production.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface NotImplemented {

}
