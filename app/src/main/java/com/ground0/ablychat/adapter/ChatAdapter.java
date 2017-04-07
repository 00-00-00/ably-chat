package com.ground0.ablychat.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ground0.ablychat.R;
import com.ground0.ablychat.databinding.ItemReceivedMessageBinding;
import com.ground0.ablychat.databinding.ItemSentMessageBinding;
import com.ground0.model.Message;
import com.ground0.model.User;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

  List<Message> data;
  User self;

  @Retention(RetentionPolicy.SOURCE) @IntDef({ VIEWTYPE_RECEIVED_MESSAGE, VIEWTYPE_SENT_MESSAGE })
  public @interface ViewType {
  }

  public static final int VIEWTYPE_SENT_MESSAGE = 0;
  public static final int VIEWTYPE_RECEIVED_MESSAGE = 1;

  public ChatAdapter(User self, List<Message> data) {
    this.data = data;
    this.self = self;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, @ViewType int viewType) {
    View view = null;
    switch (viewType) {

      case VIEWTYPE_RECEIVED_MESSAGE:
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_received_message, parent, false);
        break;
      case VIEWTYPE_SENT_MESSAGE:
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_sent_message, parent, false);
        break;
    }
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

    ViewDataBinding viewDataBinding = DataBindingUtil.bind(holder.itemView);
    switch (getItemViewType(position)) {
      case VIEWTYPE_RECEIVED_MESSAGE:
        ((ItemReceivedMessageBinding) viewDataBinding).setMessage(data.get(position));
        break;
      case VIEWTYPE_SENT_MESSAGE:
        ((ItemSentMessageBinding) viewDataBinding).setMessage(data.get(position));
        break;
    }
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @ViewType @Override public int getItemViewType(int position) {
    //sender is self => message type sent
    if (data.get(position).getFromUser().getUserName().equals(self.getUserName())) {
      return VIEWTYPE_SENT_MESSAGE;
    } else {
      return VIEWTYPE_RECEIVED_MESSAGE;
    }
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
