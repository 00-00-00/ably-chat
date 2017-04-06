package com.ground0.ablychat.core.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.ground0.model.User;

/**
 * Created by zer0 on 7/4/17.
 */

public abstract class BaseService extends Service {

  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);

    return START_STICKY;
  }

  public BaseApplication getBaseApplication() {
    return (BaseApplication) getApplication();
  }

  public User getSelf() {
    return getBaseApplication().getSelf();
  }
}
