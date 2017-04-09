package com.ground0.ablychat.util;

import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zer0 on 20/1/17.
 */

public class ScrollCallbackGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

  private int initialTopViewLayoutHeight = -1;

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({ KEYBOARD_HIDDEN, KEYBOARD_POP_DOWN, KEYBOARD_POP_UP })
  public @interface KeyboardStates {
  }

  private static final int KEYBOARD_HIDDEN = 0;
  private static final int KEYBOARD_POP_UP = 1;
  private static final int KEYBOARD_POP_DOWN = 2;
  private @KeyboardStates int PREVIOUS_STATE = -1;

  private static final String TAG = "ScrollLayoutListener";

  Callback callback;
  View topView;

  public ScrollCallbackGlobalLayoutListener(View topView, Callback callback) {
    this.callback = callback;
    this.topView = topView;
  }

  @Override public void onGlobalLayout() {

    Log.d(TAG, "top view height = " + initialTopViewLayoutHeight);
    if (initialTopViewLayoutHeight == -1) {
      initialTopViewLayoutHeight = topView.getHeight();
      Log.d(TAG, "initialiser");
    } else {
      Log.d(TAG, "top view height = " + topView.getHeight());
      //Check if there is a difference between the intialTopViewLayoutHeight and current height
      if (initialTopViewLayoutHeight != topView.getHeight()) {//implies keyboard launched
        Log.d(TAG, "height difference check returs true");
        scrollIfFlagSet(KEYBOARD_POP_UP);
      } else {
        Log.d(TAG, "keiyboard is hidded");
        scrollIfFlagSet(KEYBOARD_POP_DOWN);
      }
    }
  }

  private void scrollIfFlagSet(@KeyboardStates int state) {
    if (state != PREVIOUS_STATE && state == KEYBOARD_POP_UP) {
      callback.scrollToLast();
    }
    PREVIOUS_STATE = state;
  }

  public interface Callback {
    void scrollToLast();
  }
}
