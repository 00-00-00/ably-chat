package com.ground0.ablychat.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/**
 * Created by zer0 on 7/4/17.
 */

public class ColorUtil {
  /**
   * @param color of the background
   * @return true if the color needs to be light and false of the color should be dark
   */
  public static boolean getInverseTextColor(@ColorInt int color) {
    double a = 1
        - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;

    return a > 0.5;
  }
}
