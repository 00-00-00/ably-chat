package com.ground0.ablychat.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
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
    ButterKnife.bind(this);
    activityLoginBinding.setViewModel(viewModel);
  }

  @OnEditorAction(R.id.a_login_last_name) boolean onEditorAction(int actionId) {
    if (actionId == EditorInfo.IME_ACTION_DONE) viewModel.signIn(null);
    return true;
  }

  public void nextActivity() {
    Intent intent = new Intent(this, ChatListActivity.class);
    startActivity(intent);
    finish();
  }
}
