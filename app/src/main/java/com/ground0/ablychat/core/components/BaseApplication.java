package com.ground0.ablychat.core.components;

import android.app.Application;
import android.content.ContextWrapper;
import com.ground0.model.User;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by zer0 on 6/4/17.
 */

public class BaseApplication extends Application {

  UserIdentity userIdentity = new UserIdentity();

  @Override public void onCreate() {
    super.onCreate();
    initPrefs();
  }

  public User getSelf() {
    return userIdentity == null ? null : userIdentity.getSelf();
  }

  public void setSelf(User user) {
    if (userIdentity != null && user != null) {
      userIdentity.setSelf(user);
    }
  }

  protected void initPrefs() {
    new Prefs.Builder().setContext(this)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(getPackageName())
        .setUseDefaultSharedPreference(true)
        .build();
  }
}
