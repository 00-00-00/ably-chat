package com.ground0.ablychat.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zer0 on 8/4/17.
 */

public class TimeUtil {

  /***
   * "Time" is the fourth track from the English progressive rock band Pink Floyd's 1973 album
   * The Dark Side of the Moon, and the only song on the album credited to all four members of the band,
   * though the lyrics were written by Roger Waters.
   * It is the final Pink Floyd song credited to all four members and the last to feature Richard Wright on lead vocals
   * until "Wearing the Inside Out" on The Division Bell. This song is about how time can slip by,
   * but many people do not realise it until it is too late. Roger Waters got the idea when he realised
   * he was no longer preparing for anything in life, but was right in the middle of it.
   * He has described this realisation taking place at ages 28 and 29 in various interviews.
   * It is noted for its long introductory passage of clocks chiming and alarms ringing,
   * recorded as a quadrophonic test by Alan Parsons, not specifically for the album.
   * @param millis time in millis
   * @return Formatted string of time
   */
  public static String getTime(Long millis) {
    Date date = new Date(millis);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    if (isSameDay(calendar, Calendar.getInstance())) {
      DateFormat format = SimpleDateFormat.getTimeInstance();
      return format.format(date);
    }

    DateFormat format = SimpleDateFormat.getDateTimeInstance();
    return format.format(date);
  }

  /**
   * <p>Checks if two calendars represent the same day ignoring time.</p>
   *
   * @param cal1 the first calendar, not altered, not null
   * @param cal2 the second calendar, not altered, not null
   * @return true if they represent the same day
   * @throws IllegalArgumentException if either calendar is <code>null</code>
   */
  public static boolean isSameDay(Calendar cal1, Calendar cal2) {
    if (cal1 == null || cal2 == null) {
      throw new IllegalArgumentException("The dates must not be null");
    }
    return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(
        Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
  }
}
