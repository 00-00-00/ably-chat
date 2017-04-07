package com.ground0.ablychat.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ground0.ablychat.R;
import com.ground0.ablychat.databinding.ItemThreadBinding;
import com.ground0.ablychat.viewmodel.MessageThreadViewModelFactory;
import com.ground0.model.MessageThread;
import java.util.List;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<MessageThread> data;
  private MessageThreadViewModelFactory messageThreadViewModelFactory;
  MessageThreadViewModelFactory.MessageThreadViewModelHandler handler;

  public ChatListRecyclerViewAdapter(
      MessageThreadViewModelFactory.MessageThreadViewModelHandler handler,
      List<MessageThread> data) {
    this.data = data;
    this.handler = handler;
    messageThreadViewModelFactory = new MessageThreadViewModelFactory();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thread, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ItemThreadBinding itemThreadBinding = DataBindingUtil.bind(holder.itemView);
    itemThreadBinding.setViewModel(
        messageThreadViewModelFactory.createItemViewModel(handler, data.get(position)));
  }

  @Override public int getItemCount() {
    return data.size();
  }

  private class ViewHolder extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
