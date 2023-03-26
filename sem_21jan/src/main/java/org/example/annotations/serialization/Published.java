package org.example.annotations.serialization;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Published {
    String value() default "";
}
