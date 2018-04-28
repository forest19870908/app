package Annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

	String name();

	String required() default "false";

	String type() default "textbox";

	String valid() default "";

	String hidden() default "false";

}
