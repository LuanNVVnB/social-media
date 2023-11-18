package com.example.cnd.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * File: com.example.cnd.validator
 * Description:  <div style="font-weight: bold; color: #0073e6;"> EnumKeysValidator </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
 **/
public class EnumKeysValidator implements ConstraintValidator<EnumKeys, Object> {
    private Class<?> constantClass;
    private String methodName;

    @Override
    public void initialize(EnumKeys parameters) {
        constantClass = parameters.value();
        methodName = parameters.methodName();
    }

    /**
     * Validates an object using a dynamically specified method.
     * The validation is performed by invoking a method on the class specified by constantClass
     * with the provided methodName and the object to be validated.
     *
     * @param obj                        The object to be validated. If null, it is considered valid.
     * @param constraintValidatorContext The context in which the validation is performed.
     * @return {@code true} if the object is considered valid, {@code false} otherwise.
     * @throws RuntimeException If there are issues with method reflection, such as method not found,
     *                          invocation target exception, or illegal access.
     */
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        try {
            // Check if the object is null, and if so, consider it valid
            if (Objects.isNull(obj)) {
                return true;
            }

            // Get the method with the specified name from the constantClass
            Method method = constantClass.getMethod(methodName, Object.class);

            // Invoke the method with the provided object as an argument
            // The method should return a Boolean value indicating validity
            return (Boolean) method.invoke(null, obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            // If any exceptions occur during reflection, wrap them in a RuntimeException
            throw new RuntimeException(e);
        }
    }
}
