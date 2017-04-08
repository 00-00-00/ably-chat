package com.ground0.ablychat.core.binding;

/**
 * Created by gauravs1 on 2/9/15.
 */
public interface Validator<T> {
    String MANDATORY = "Mandatory Field";
    boolean validate(T value, BindableErrorField errorField);
}
