package com.ground0.ablychat.core.components;

import android.support.v4.app.Fragment;

/**
 * Created by zer0 on 6/4/17.
 */

public abstract class BaseFragment<T> extends Fragment {

  public T getActualActivity() {
    return (T) getActivity();
  }
}
