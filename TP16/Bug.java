package TP16;

import java.lang.annotation.*;

@Repeatable(Bugs.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bug {
    int id();
    String description();
    String status() default "OPEN";
}
