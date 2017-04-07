package com.ground0.ablychat.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.ablychat.R;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.databinding.ActivityChatBinding;
import com.ground0.ablychat.viewmodel.ChatActivityViewModel;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatActivity extends BaseActivity {

  ChatActivityViewModel viewModel = new ChatActivityViewModel();

  public static final String CHAT_ACTIVITY_TO_USERNAME =
      "com.ground0.ablychat.chatactivity_to_username_key";
  public static final String CHAT_ACTIVITY_THREAD_ID =
      "com.ground0.ablychat.chatactivity_to_thread_id";

  @BindView(R.id.a_chat_recycler) RecyclerView recyclerView;

  @Override public void registerViewModel() {
    viewModel.register(this);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel.onRestoreState(getIntent().getExtras());
    ActivityChatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
    binding.setViewModel(viewModel);
    ButterKnife.bind(this);
    initRecyclerView();
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    viewModel.onRestoreState(savedInstanceState);
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    viewModel.onSaveState(outState);
    super.onSaveInstanceState(outState);
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(viewModel.getAdapter());
  }
}
