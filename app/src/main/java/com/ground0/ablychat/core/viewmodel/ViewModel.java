package com.ground0.ablychat.core.viewmodel;

/**
 * Created by zer0 on 6/4/17.
 */

public interface ViewModel<T> {

  void register(T component);

  void afterRegister();
}
