package com.ground0.ablychat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ground0.ablychat.R;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.viewmodel.ChatListActivityViewModel;
import com.ground0.model.MessageThread;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatListActivity extends BaseActivity {

  ChatListActivityViewModel viewModel = new ChatListActivityViewModel();
  @BindView(R.id.a_chat_list_recycler) RecyclerView recyclerView;

  @Override public void registerViewModel() {
    viewModel.register(this);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat_list);
    ButterKnife.bind(this);
    initRecyclerView();
  }

  @OnClick(R.id.a_chat_list_button) public void onStartChatClick(View view) {
    new MaterialDialog.Builder(this).title(R.string.start_chat)
        .input(getString(R.string.user_name), "", true, (dialog, input) -> {
          Intent intent = new Intent(this, ChatActivity.class);
          intent.putExtra(ChatActivity.CHAT_ACTIVITY_THREAD_ID,
              MessageThread.generateId(getBaseApplication().getSelf().getUserName(),
                  input.toString()));
          intent.putExtra(ChatActivity.CHAT_ACTIVITY_TO_USERNAME, input.toString());
          startActivity(intent);
        })
        .positiveText("Start")
        .show();
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(viewModel.getAdapter());
  }

  public void nextActivity(String threadId, String userName) {
    Intent intent = new Intent(this, ChatActivity.class);
    intent.putExtra(ChatActivity.CHAT_ACTIVITY_THREAD_ID, threadId);
    intent.putExtra(ChatActivity.CHAT_ACTIVITY_TO_USERNAME, userName);

    startActivity(intent);
  }
}
