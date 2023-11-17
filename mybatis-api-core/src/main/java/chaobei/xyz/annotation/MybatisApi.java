package chaobei.xyz.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisApi {

    String value() default "";

}
