package xyz.chaobei.annotation;


import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MybatisRequestMapping {

    String value() default "";

}
