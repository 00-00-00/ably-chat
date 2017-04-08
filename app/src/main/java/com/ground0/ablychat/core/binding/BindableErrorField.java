package com.ground0.ablychat.core.binding;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

/**
 * Created by gauravs1 on 2/9/15.
 */
public class BindableErrorField {

  public BindableErrorField() {

  }

  public TextInputLayout getTextInputLayout() {
    return textInputLayout;
  }

  public void setTextInputLayout(TextInputLayout textInputLayout) {
    this.textInputLayout = textInputLayout;
  }

  private TextInputLayout textInputLayout;
  private EditText editText;

  public void setError(String error) {
    //textInputLayout.setError(null);
    //textInputLayout.setErrorEnabled(false);
    if (textInputLayout != null) {
      textInputLayout.setErrorEnabled(true);
      textInputLayout.setError(error);
    } else if (editText != null) {
      editText.setError(error);
    }
  }

  public void clear() {
    if (textInputLayout != null) {
      textInputLayout.setError(null);
      textInputLayout.setErrorEnabled(false);
      //textInputLayout.setErrorEnabled(true);
    } else if (editText != null) {
      editText.setError(null);
    }
  }

  public void setTextInputLayout(EditText view) {
    this.editText = view;
  }
}
