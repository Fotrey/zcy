package priv.zcy.controller.returning_wrapper;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersonalResponseBodyWrapper {
}
