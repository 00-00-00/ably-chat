package com.ground0.ablychat.viewmodel;

import android.support.annotation.Nullable;
import android.view.View;
import com.ground0.ablychat.activity.LoginActivity;
import com.ground0.ablychat.core.viewmodel.AbstractActivityViewModel;
import com.ground0.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zer0 on 7/4/17.
 */

public class LoginActivityViewModel extends AbstractActivityViewModel<LoginActivity> {

  private User user = new User();

  public void signIn(@Nullable View view) {
    // TODO: 7/4/17 Add static validators and binding adapters
    if (StringUtils.isNotBlank(user.getUserName())) {
      if (StringUtils.isBlank(user.getFirstName()) && StringUtils.isBlank(user.getLastName())) {
        user.setFirstName("User");
        user.setLastName("1");
      }
      getApplication().setSelf(user);
      getActivity().nextActivity();
    }
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
