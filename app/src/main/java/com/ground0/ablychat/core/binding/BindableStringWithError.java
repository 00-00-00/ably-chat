package com.ground0.ablychat.core.binding;

import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gauravs1 on 2/9/15.
 */
public class BindableStringWithError extends BindableString {
    private List<Validator<String>> validators;
    private BindableErrorField errorField;
	  public static int CLEAR_ERROR_COLOR;

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    private EditText editText;

    public BindableStringWithError(List<Validator<String>> validators, BindableErrorField errorField){
        this.validators = validators;
        this.errorField = errorField;
    }

    public BindableStringWithError(Validator<String> validator, BindableErrorField errorField){
        this(new ArrayList<Validator<String>>(){{
            add(validator);
        }
        }, errorField);
    }

    public void clearError(){
        if(errorField != null){
            errorField.clear();
        }
    }
    public boolean validate(){
        boolean hasError = false;
        for (Validator validator: validators) {
            if(!validator.validate(value, errorField)){
                hasError = true;
                break;
            }
        }
//        if(editText != null)
//        editText.getBackground().setColorFilter(
//                hasError ? Color.RED : CLEAR_ERROR_COLOR, PorterDuff.Mode.SRC_ATOP);
        if(!hasError)errorField.clear();
        return !hasError;
    }

    public List<Validator<String>> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator<String>> validators) {
        this.validators = validators;
    }

    public BindableErrorField getErrorField(){
        return errorField;
    }
}
