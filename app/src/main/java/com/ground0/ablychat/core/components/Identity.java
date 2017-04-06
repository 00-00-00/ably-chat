package com.ground0.ablychat.core.components;

import com.bluelinelabs.logansquare.internal.objectmappers.ObjectMapper;
import com.ground0.model.User;
import com.pixplicity.easyprefs.library.Prefs;
import java.io.IOException;

/**
 * Created by zer0 on 7/4/17.
 */

public abstract class Identity {

  ObjectMapper objectMapper;
  private static final String TAG = "AppIdentity";

  protected void addOrUpdateIdentity(String key, Object value) {
    try {
      Prefs.putString(key, objectMapper.serialize(value));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected User getIdentity(String key) {
    try {
      String patientString = Prefs.getString(key, null);
      if (patientString == null) return null;
      return (User) objectMapper.parse(patientString);
    } catch (IOException e) {
      return null;
    }
  }

  protected void deleteIdentity(String key) {
    Prefs.remove(key);
  }

  protected abstract void clear();
}
