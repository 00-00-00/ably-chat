package com.ground0.ablychat.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.ground0.ablychat.R;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.databinding.ActivityLoginBinding;
import com.ground0.ablychat.viewmodel.LoginActivityViewModel;

/**
 * Created by zer0 on 7/4/17.
 */

public class LoginActivity extends BaseActivity {

  LoginActivityViewModel viewModel = new LoginActivityViewModel();

  @Override public void registerViewModel() {
    viewModel.register(this);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityLoginBinding activityLoginBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_login);
    activityLoginBinding.setViewModel(viewModel);
  }

  public void nextActivity() {
    Intent intent = new Intent(this, ChatListActivity.class);
    startActivity(intent);
    finish();
  }
}
