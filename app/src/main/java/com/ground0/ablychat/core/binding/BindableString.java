package com.ground0.ablychat.core.binding;

import android.databinding.BaseObservable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gauravs1 on 31/8/15.
 */
public class BindableString extends BaseObservable {
  protected String value;

  public BindableString(String value) {
    this.value = value;
  }

  public BindableString() {

  }

  public String get() {
    return value != null ? value : "";
  }

  public void set(String value) {
    if (this.value == null && value == null) return;
    if ((this.value == null && value != null) || !this.value.equals(value)) {
      this.value = StringUtils.isEmpty(value) ? null : value;
      notifyChange();
    }
  }


  public boolean isEmpty() {
    return value == null || value.isEmpty();
  }

  public String toString() {
    return value;
  }

  public String getNullableValue() {
    return value;
  }
}
