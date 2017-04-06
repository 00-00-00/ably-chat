package com.ground0.ablychat.core.viewmodel;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;

/**
 * Created by zer0 on 6/4/17.
 */

public abstract class AbstractViewModel<T> implements ViewModel<T> {

  @Nullable WeakReference<T> registeredView;

  /***
   * This function has to be called register the viewmodel
   * @param component
   */
  @Override public void register(T component) {
    registeredView = new WeakReference<T>(component);
    afterRegister();
  }

  @Override public void afterRegister() {

  }
}
