package com.ground0.ablychat.core.binding;

import android.text.TextUtils;

/**
 * Created by gauravs1 on 2/9/15.
 */
public class StaticValidators {
  private abstract static class AbstractValidator implements Validator<String> {

  }

  public static class MandatoryValidator extends AbstractValidator {

    public boolean validate(String value, BindableErrorField errorField) {
      if (TextUtils.isEmpty(value)) {
        errorField.setError("Mandatory field");
        return false;
      }
      return true;
    }
  }

  public static class LengthValidator extends AbstractValidator {
    private int expectedLength;

    public LengthValidator(int expectedLength) {
      this.expectedLength = expectedLength;
    }

    public boolean validate(String value, BindableErrorField errorField) {
      if (value.length() < expectedLength) {
        errorField.setError("Minimum length: " + expectedLength);
        return false;
      }
      return true;
    }
  }
}
