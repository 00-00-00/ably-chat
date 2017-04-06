package com.ground0.ablychat.core.components;

import android.app.Application;
import com.ground0.model.User;

/**
 * Created by zer0 on 6/4/17.
 */

public class BaseApplication extends Application {

  UserIdentity userIdentity;

  @Override public void onCreate() {
    super.onCreate();
  }



  public User getSelf() {
    return userIdentity == null ? null : userIdentity.getSelf();
  }

  public void setSelf(User user) {
    if (userIdentity != null) {
      userIdentity.setSelf(user);
    }
  }
}
