package com.ground0.ablychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground0.ablychat.core.components.BaseActivity;
import com.ground0.ablychat.service.ChatService;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.Message;
import com.ground0.model.User;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import java.io.IOException;

public class MainActivity extends BaseActivity {

  @BindView(R.id.text) TextView textView;
  ObjectMapper objectMapper = new ObjectMapper();

  @Override public void registerViewModel() {

  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    startService(new Intent(this, ChatService.class));
  }

  @OnClick(R.id.text) public void onTextClick(View view) {
    AblyRealtime ablyRealtime = null;
    try {
      ablyRealtime = new AblyRealtime(Constants.ABLY_API_KEY);
      Channel channel = ablyRealtime.channels.get("test_channel"/*getSelf().getId().toString()*/);
      channel.publish("message", getTestMessage(), new CompletionListener() {

        @Override public void onSuccess() {
          //Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
        }

        @Override public void onError(ErrorInfo reason) {
          //Toast.makeText(MainActivity.this, "Message not sent, error occurred: " + reason.message,
          //    Toast.LENGTH_SHORT).show();
        }
      });
    } catch (AblyException e) {
      e.printStackTrace();
    }
  }

  public String getTestMessage() {
    User user = new User();
    user.setUserName(user.getFullName());
    user.setFirstName("Don");
    user.setLastName("Corleone");

    Message message = new Message();

    message.setMessageId(System.currentTimeMillis());
    message.setToUser(user);
    message.setFromUser(getBaseApplication().getSelf());
    message.setMessageId(3L);
    message.setSendTimeStamp(System.currentTimeMillis());
    message.setMessage("Test Message");

    try {
      return objectMapper.writeValueAsString(message);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
