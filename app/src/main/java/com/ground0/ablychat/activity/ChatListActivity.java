package com.ground0.ablychat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.ablychat.R;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.viewmodel.ChatListActivityViewModel;

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

  private void initRecyclerView() {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(viewModel.getAdapter());
  }
}
