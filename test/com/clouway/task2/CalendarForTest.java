package com.clouway.task2;


import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class CalendarForTest {
  private Calendar calendar=new GregorianCalendar();

  public Date getDate(Integer day,Integer month, Integer year){
    calendar.set(Calendar.DAY_OF_MONTH,day);
    calendar.set(Calendar.MONTH,(month-1));
    calendar.set(Calendar.YEAR,year);
    java.util.Date javaDate=calendar.getTime();
    java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
    return sqlDate;
  }
}
