package com.ground0.ablychat.viewmodel;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import com.ground0.ablychat.activity.LoginActivity;
import com.ground0.ablychat.core.binding.BindableErrorField;
import com.ground0.ablychat.core.binding.BindableStringWithError;
import com.ground0.ablychat.core.binding.StaticValidators;
import com.ground0.ablychat.core.viewmodel.AbstractActivityViewModel;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zer0 on 7/4/17.
 */

public class LoginActivityViewModel extends AbstractActivityViewModel<LoginActivity> {

  private User user = new User();

  public BindableErrorField userNameError = new BindableErrorField();
  public BindableStringWithError userName =
      new BindableStringWithError(new StaticValidators.MandatoryValidator(), userNameError);

  @Override public void afterRegister() {
    super.afterRegister();
    checkIfLoggedIn();
  }

  public void signIn(@Nullable View view) {
    if (!userName.validate()) return;

    if (StringUtils.isNotBlank(user.getUserName())) {
      if (StringUtils.isBlank(user.getFirstName()) && StringUtils.isBlank(user.getLastName())) {
        user.setFirstName("User");
        user.setLastName("1");
      }
      getApplication().setSelf(user);
      getApplication().sendBroadcast(new Intent(Constants.SELF_REFRESHED_ACTION));
      getActivity().nextActivity();
    }
  }

  private void checkIfLoggedIn() {
    if (getApplication().getSelf() != null && StringUtils.isNotBlank(
        getApplication().getSelf().getUserName())) {
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
