package com.ground0.ablychat.core.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zer0 on 6/4/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerViewModel();
  }

  public abstract void registerViewModel();

  protected BaseApplication getBaseApplication() {
    return (BaseApplication) getApplication();
  }
}
