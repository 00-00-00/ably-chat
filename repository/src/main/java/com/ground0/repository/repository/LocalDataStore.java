package com.ground0.repository.repository;

import android.content.Context;
import android.util.Log;
import com.ground0.model.Message;
import com.ground0.model.MessageThread;
import com.ground0.model.User;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by zer0 on 6/4/17.
 */

public class LocalDataStore implements Repository {

  public LocalDataStore(Context context) {
    Realm.init(context);
    /*RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(0)
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(realmConfiguration);*/
  }

  @Override public Observable<Long> saveMessage(String selfId, final Message message) {

    Log.d(getClass().getSimpleName(), "Saving message");

    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(message));

    MessageThread messageThread = new MessageThread();
    messageThread.readFrom(message);
    /*If message is not from self then store the address of thread from message; (i.e. inverted)
    and add last message to thread
     */
    if (!message.getFromUser().getUserName().equals(selfId)) {
      messageThread.setFromUser(message.getToUser());
      messageThread.setToUser(message.getFromUser());
      messageThread.setLastMessage(message);
    } else {
      RealmResults<Message> results = realm.where(Message.class)
          .equalTo("threadId", messageThread.getId())
          .notEqualTo("fromUser.userName", selfId)
          .findAllSorted("sendTimeStamp", Sort.DESCENDING);
      if (results.size() != 0) messageThread.setLastMessage(results.get(0));
    }
    realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(messageThread));

    realm.close();

    return Observable.just(message.getMessageId());
  }

  public Observable<RealmResults<Message>> getMessages(String threadId) {
    Realm realm = Realm.getDefaultInstance();

    return realm.where(Message.class)
        .equalTo("threadId", threadId)
        .findAllSorted("sendTimeStamp")
        .asObservable();
  }

  @Override public Observable<RealmResults<MessageThread>> getChatList(String selfId) {
    Realm realm = Realm.getDefaultInstance();
    return realm.where(MessageThread.class)
        .equalTo("fromUser.userName", selfId)
        .distinct("id")
        .sort("lastMessage.receivedTimeStamp", Sort.DESCENDING)
        .asObservable();
  }

  @Override public Observable<User> getUser(String userName) {
    Realm realm = Realm.getDefaultInstance();
    return Observable.just(realm.where(User.class).equalTo("userName", userName).findFirst());
  }
}
