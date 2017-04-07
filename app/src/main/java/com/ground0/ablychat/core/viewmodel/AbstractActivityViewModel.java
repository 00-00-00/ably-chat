package com.ground0.ablychat.core.viewmodel;

import android.os.Bundle;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.core.components.BaseApplication;

/**
 * Created by zer0 on 6/4/17.
 */

public abstract class AbstractActivityViewModel<T extends BaseActivity>
    extends AbstractViewModel<T> {

  protected BaseApplication getApplication() {
    return (BaseApplication) registeredView.get().getApplication();
  }

  protected T getActivity() {
    return registeredView == null ? null : registeredView.get();
  }

  public void onRestoreState(Bundle savedInstanceState) {

  }

  public void onSaveState(Bundle outstate) {

  }
}
