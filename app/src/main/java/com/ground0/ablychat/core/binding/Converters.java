package com.ground0.ablychat.core.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.Html;
import android.widget.EditText;
import com.ground0.ablychat.R;

/**
 * Created by gauravs1 on 31/8/15.
 */
public class Converters {
  @BindingConversion public static String convertBindableToString(BindableString bindableString) {
    return bindableString.get();
  }

  @BindingConversion public static String convertIntTOString(int input) {
    return Integer.toString(input);
  }

  @BindingAdapter({ "app:binding" })
  public static void bindEditText(EditText view, final BindableString bindableString) {
    setHintText(view);
    if (view.getTag(R.id.binded) == null) {
      view.setTag(R.id.binded, true);
      view.addTextChangedListener(new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
          bindableString.set(s.toString());
        }
      });
    }
    if (bindableString == null) return;
    String newValue = bindableString.get();
    if (!view.getText().toString().equals(newValue)) {
      view.setText(newValue);
    }
  }

  private static void setHintText(EditText view) {
    if (view.getHint() == null) return;
    String hint = view.getHint().toString();
    if (hint != null && hint.endsWith("*")) {
      view.setHint(Html.fromHtml(
          hint.substring(0, hint.lastIndexOf("*")) + "&lt;sup>&lt;small> * &lt;/small>&lt;/sup>"));
    }
  }

  @BindingAdapter({ "app:error" })
  public static void bindErrorField(TextInputLayout view, final BindableErrorField errorField) {
    if (view.getTag(R.id.binded) == null) {
      view.setTag(R.id.binded, true);
      errorField.setTextInputLayout(view);
    }
  }

  @BindingAdapter({ "app:error" })
  public static void bindErrorField(EditText view, final BindableStringWithError errorField) {
    if (view.getTag(R.id.binded) == null) {
      view.setTag(R.id.binded, true);
      errorField.setEditText(view);
    }
  }

  @BindingAdapter({ "app:binding" })
  public static void bindError(EditText view, final BindableStringWithError bindableString) {
    setHintText(view);
    bindableString.setEditText(view);
    if (view.getTag(R.id.binded) == null) {
      view.setTag(R.id.binded, true);
      //            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      //                @Override
      //                public void onFocusChange(View v, boolean hasFocus) {
      //                    if(!hasFocus){
      //                        bindableString.validate();
      //                    }
      //                }
      //            });
      view.addTextChangedListener(new TextWatcherAdapter() {
        @Override public void afterTextChanged(Editable s) {
          bindableString.set(s.toString());
          bindableString.clearError();
        }
      });
    }
    if (bindableString == null) return;
    String newValue = bindableString.get();
    if (!view.getText().toString().equals(newValue)) {
      view.setText(newValue);
    }
  }
}