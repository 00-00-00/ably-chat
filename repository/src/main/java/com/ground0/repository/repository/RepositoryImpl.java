package com.ground0.repository.repository;

import android.content.Context;
import com.ground0.model.Message;
import com.ground0.model.MessageThread;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by zer0 on 6/4/17.
 */

public class RepositoryImpl implements Repository {

  static RepositoryImpl instance;
  Repository repository;

  private RepositoryImpl(Context context) {
    repository = new LocalDataStore(context);
  }

  public static RepositoryImpl getInstance(Context context) {
    if (instance == null) {
      instance = new RepositoryImpl(context);
    }
    return instance;
  }

  @Override public Observable<Long> saveMessage(Message message) {
    return repository.saveMessage(message);
  }

  @Override public Observable<RealmResults<Message>> getMessages(Long threadId) {
    return repository.getMessages(threadId);
  }

  @Override public Observable<RealmResults<MessageThread>> getChatList(Long selfId) {
    return repository.getChatList(selfId);
  }
}
