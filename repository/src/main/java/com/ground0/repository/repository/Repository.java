package com.ground0.repository.repository;

import com.ground0.model.Message;
import com.ground0.model.MessageThread;
import com.ground0.model.User;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by zer0 on 6/4/17.
 */

public interface Repository {

  public Observable<Long> saveMessage(Message message);

  public Observable<RealmResults<Message>> getMessages(String threadId);

  public Observable<RealmResults<MessageThread>> getChatList(String selfId);

  public Observable<User> getUser(String userName);
}
