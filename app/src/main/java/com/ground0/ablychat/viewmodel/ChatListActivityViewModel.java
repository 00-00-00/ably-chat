package com.ground0.ablychat.viewmodel;

import adapter.ChatListRecyclerViewAdapter;
import com.ground0.ablychat.activity.ChatListActivity;
import com.ground0.ablychat.core.viewmodel.AbstractActivityViewModel;
import com.ground0.model.MessageThread;
import com.ground0.repository.repository.Repository;
import com.ground0.repository.repository.RepositoryImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatListActivityViewModel extends AbstractActivityViewModel<ChatListActivity> {

  private ChatListRecyclerViewAdapter adapter;
  private List<MessageThread> messageThreads = new ArrayList<>();
  private Repository repository;

  public ChatListRecyclerViewAdapter getAdapter() {
    if (adapter == null) adapter = new ChatListRecyclerViewAdapter(messageThreads);
    return adapter;
  }

  @Override public void afterRegister() {
    super.afterRegister();
    repository = RepositoryImpl.getInstance(getActivity());
    fetchData();
  }

  private void fetchData() {
    repository.getChatList(getApplication().getSelf().getUserName()).subscribe(messageThreads1 -> {
      messageThreads.clear();
      messageThreads.addAll(messageThreads1);
      if (adapter != null)
      adapter.notifyDataSetChanged();
    });
  }
}
