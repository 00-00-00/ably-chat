package com.ground0.ablychat.core.components;

import com.ground0.model.User;

/**
 * Created by zer0 on 7/4/17.
 */

public class UserIdentity extends Identity {

  public static final String SELF_OBJECT_KEY = "self_obj_key";
  public static final String SELF_KEY = "self_id_key";
  private User self;

  public User getSelf() {
    if (self == null) {
      self = getIdentity(SELF_OBJECT_KEY);
    }
    return self;
  }

  public void setSelf(User self) {
    this.self = self;
    addOrUpdateIdentity(SELF_OBJECT_KEY, self);
    addOrUpdateIdentity(SELF_KEY, self.getUserName());
  }

  @Override protected void clear() {
    deleteIdentity(SELF_KEY);
    deleteIdentity(SELF_OBJECT_KEY);
    this.self = null;
  }
}
