package code.gui;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.Timer;

import code.gui.events.UpdateTimeEvent;
import code.util.core.StringUtil;

public class Clock {

    private static final String ARIAL = "Arial";
    private static final String SAMPLE_SEPARATOR = "_";
    private static final String SEPARATOR_DATE_TIME = " ";
    private static final String SEPARATOR_DATE = "/";
    private static final String SEPARATOR = ":";
    private static final int SECOND_MILLIS = 1000;
//    private static final Color COLOR = new Color(0,0,255);
    private static final int DEFAULT_NB_CHARS = 10;
    private static final int HEIGHT_TIME = 15;

    private Timer timer;
    private TextField component;

    public Clock() {
        component = new TextField(DEFAULT_NB_CHARS);
        component.setEditable(false);
        component.setFont(new Font(ARIAL,Font.PLAIN,HEIGHT_TIME));
        component.setForeground(new Color(0,0,255));
        timer = new Timer(SECOND_MILLIS, new UpdateTimeEvent(this));
        timer.start();
    }

    public void setTimeText() {
        component.setText(getTimeText());
    }

    public static String getSimpleDateTimeText() {
        return getDateTimeText(SAMPLE_SEPARATOR, SAMPLE_SEPARATOR, SAMPLE_SEPARATOR);
    }

    public static String getDateTimeText(String _separatorDate, String _sep, String _separatorTime) {
        Calendar now_ = Calendar.getInstance();
        int y_ = now_.get(Calendar.YEAR);
        int m_ = now_.get(Calendar.MONTH) + 1;
        int d_ = now_.get(Calendar.DAY_OF_MONTH);
        int h_ = now_.get(Calendar.HOUR_OF_DAY);
        int mi_ = now_.get(Calendar.MINUTE);
        int s_ = now_.get(Calendar.SECOND);
        return StringUtil.concat(Long.toString(y_),_separatorDate,Long.toString(m_),
                _separatorDate,Long.toString(d_),_sep,Long.toString(h_),
                _separatorTime,Long.toString(mi_),_separatorTime,Long.toString(s_));
    }

    public static String getDateTimeText() {
        Calendar now_ = Calendar.getInstance();
        int y_ = now_.get(Calendar.YEAR);
        int m_ = now_.get(Calendar.MONTH) + 1;
        int d_ = now_.get(Calendar.DAY_OF_MONTH);
        String date_ = StringUtil.concat(Long.toString(d_),SEPARATOR_DATE,Long.toString(m_),SEPARATOR_DATE,Long.toString(y_));
        return StringUtil.concat(date_,SEPARATOR_DATE_TIME,getTimeText());
    }

    public static String getTimeText() {
        Calendar now_ = Calendar.getInstance();
        int h_ = now_.get(Calendar.HOUR_OF_DAY);
        int m_ = now_.get(Calendar.MINUTE);
        int s_ = now_.get(Calendar.SECOND);
        return StringUtil.concat(Long.toString(h_),SEPARATOR,Long.toString(m_),SEPARATOR,Long.toString(s_));
    }

    public static String getDateText() {
        Calendar now_ = Calendar.getInstance();
        int y_ = now_.get(Calendar.YEAR);
        int m_ = now_.get(Calendar.MONTH) + 1;
        int d_ = now_.get(Calendar.DAY_OF_MONTH);
        return StringUtil.concat(Long.toString(y_),SEPARATOR_DATE,Long.toString(m_),SEPARATOR_DATE,Long.toString(d_));
    }

    public CustComponent getComponent() {
        return component;
    }
}
