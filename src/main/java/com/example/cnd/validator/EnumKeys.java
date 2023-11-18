package com.example.cnd.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * File: com.example.cnd.validator
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Enum Keys </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
 **/
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumKeysValidator.class})
public @interface EnumKeys {
    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * Returns the class representing the validation group to which this constraint belongs.
     * The validation group allows you to apply different sets of constraints based on the context
     * in which the validation is performed.
     * <p>
     * The default implementation returns an unspecified class, and it's often used as a placeholder
     * for specifying the validation group dynamically.
     *
     * @return The class representing the validation group to which this constraint belongs.
     * A default or unspecified class may indicate dynamic validation group usage.
     * </div><hr>
     * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    Class<?> value();

    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * Returns the default error message template for when the constraint validation fails.
     * This message is used when a more specific error message is not provided.
     * <p>
     * The default implementation returns a string with the value "EnumKeys".
     *
     * @return The default error message template for the constraint.
     * </div><hr>
     * Author:  <span style="font-weight: bold; olor: #00a65a;">LuanNVV</span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    String message() default "Validate input error, try again";

    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * Returns the name of the method used for custom validation when applying the constraint.
     * This method is typically invoked via reflection during validation.
     * <p>
     * The default implementation returns an empty string, indicating that no specific method name
     * is provided, and the constraint relies on a default validation method or is applied at the field level.
     *
     * @return The name of the custom validation method associated with the constraint.
     * An empty string indicates the use of default validation or field-level validation.
     * </div><hr>
     * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    String methodName() default "";

    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * Returns an array of values to be excluded from validation.
     * When applying the constraint, these values are considered invalid.
     * <p>
     * The default implementation returns an empty array, indicating no values are excluded by default.
     *
     * @return An array of integers representing values to be excluded from validation.
     * An empty array implies no exclusion.
     * </div><hr>
     * Author:  <span style="font-weight: bold; color: #0a65a;">a</span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    int[] exclude() default {};

    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * Returns the validation groups to which this constraint belongs.
     * Validation groups allow you to apply different sets of constraints based on the context
     * in which the validation is performed.
     * <p>
     * The default implementation returns an empty array, indicating that this constraint
     * is not explicitly assigned to any validation groups.
     *
     * @return An array of classes representing the validation groups to which this constraint belongs
     * </div><hr>
     * Author:  <span style="font-weight: bold; color: #00a65;">LuanNVV</span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    Class<?>[] groups() default {};

    /**
     * File: com.example.cnd.validator
     * Description:  <div style="font-weight: bold; color: #0073e6;">
     * <p>
     * Returns the classes of payload objects that can be associated with this constraint.
     * Payload objects provide additional metadata about the validation error and are used to
     * distinguish between different error scenarios.
     * <p>
     * The default implementation returns an empty array, indicating that no specific payload classes
     * are associated with this constraint.
     *
     * @return An array of classes representing the payload objects associated with this constraint.
     * @see Payload
     *
     * </div><hr>
     * Author:  <span style="font-weight: bold; color: #00a65;">LuanNVV</span>
     * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
     **/
    Class<? extends Payload>[] payload() default {};
}
