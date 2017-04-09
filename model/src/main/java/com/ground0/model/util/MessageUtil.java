package com.ground0.model.util;

import com.ground0.model.Message;

/**
 * Created by zer0 on 9/4/17.
 *
 * "Message in a Bottle" is a song by English rock band The Police.
 * It was released as the lead single from their second studio album, Reggatta de Blanc (1979).
 * Written by the band's lead singer and bassist Sting, the song is ostensibly about
 * a story of a castaway on an island, who sends out a message in a bottle to seek love.
 * A year later, he has not received any sort of response, and despairs,
 * thinking he is destined to be alone.
 * The next day, he sees "a hundred billion bottles" on the shore, finding out that
 * there are more people like him out there.
 *
 * The song was the first of their five UK number one singles.
 * Rolling Stone ranked it number 65 on its list of the "100 Greatest Guitar Songs of All Time".
 */

public class MessageUtil {

  public static String getFormattedState(@Message.MessageState String messageState) {
    switch (messageState) {
      case Message.MESSAGE_STAT_FAILED:
        return "Failed";
      case Message.MESSAGE_STAT_SENT:
        return "Sent";
      case Message.MESSAGE_STAT_SENDING:
        return "Sending";
      default:
        return "";
    }
  }
}
