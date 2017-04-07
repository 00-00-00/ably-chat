package com.ground0.ablychat.core.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground0.model.User;
import com.pixplicity.easyprefs.library.Prefs;
import java.io.IOException;

/**
 * Created by zer0 on 7/4/17.
 */

public abstract class Identity {

  private static final String TAG = "AppIdentity";
  ObjectMapper objectMapper = new ObjectMapper();

  protected void addOrUpdateIdentity(String key, Object value) {
    try {
      Prefs.putString(key, objectMapper.writeValueAsString(value));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected User getIdentity(String key) {
    try {
      String patientString = Prefs.getString(key, null);
      if (patientString == null) return null;
      return objectMapper.readValue(patientString, User.class);
    } catch (IOException e) {
      return null;
    }
  }

  protected void deleteIdentity(String key) {
    Prefs.remove(key);
  }

  protected abstract void clear();
}
