package com.ground0.ablychat.viewmodel;

import android.content.Intent;
import com.ground0.ablychat.activity.ChatListActivity;
import com.ground0.ablychat.adapter.ChatListRecyclerViewAdapter;
import com.ground0.ablychat.core.viewmodel.AbstractActivityViewModel;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.MessageThread;
import com.ground0.repository.repository.Repository;
import com.ground0.repository.repository.RepositoryImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatListActivityViewModel extends AbstractActivityViewModel<ChatListActivity>
    implements MessageThreadViewModelFactory.MessageThreadViewModelHandler {

  private ChatListRecyclerViewAdapter adapter;
  private List<MessageThread> messageThreads = new ArrayList<>();
  private Repository repository;

  public ChatListRecyclerViewAdapter getAdapter() {
    if (adapter == null) adapter = new ChatListRecyclerViewAdapter(this, messageThreads);
    return adapter;
  }

  @Override public void afterRegister() {
    super.afterRegister();
    repository = RepositoryImpl.getInstance(getActivity());
    fetchData();
  }

  @Override public void openDetail(MessageThread messageThread) {
    getActivity().nextActivity(messageThread.getId(), messageThread.getToUser().getUserName());
  }

  private void fetchData() {
    repository.getChatList(getApplication().getSelf().getUserName()).subscribe(messageThreads1 -> {
      messageThreads.clear();
      messageThreads.addAll(messageThreads1);
      if (adapter != null) adapter.notifyDataSetChanged();
    });
  }

  public void logOut() {
    getApplication().clearSelf();
    getActivity().goToLoginActivity();
    getApplication().sendBroadcast(new Intent(Constants.SELF_CLEARED_ACTION));
  }
}
