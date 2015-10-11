package com.su.annotation;
import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;
@Target({java.lang.annotation.ElementType.METHOD,
		 java.lang.annotation.ElementType.FIELD,
		 java.lang.annotation.ElementType.PARAMETER,
		 java.lang.annotation.ElementType.ANNOTATION_TYPE,
		 java.lang.annotation.ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)

@Constraint(validatedBy = {})

public @interface NotEmpty {
	public String message() default "The field is not empty";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
