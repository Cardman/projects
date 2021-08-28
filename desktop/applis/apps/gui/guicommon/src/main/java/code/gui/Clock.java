package code.gui;


import code.gui.events.UpdateTimeEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractThreadFactory;

public class Clock {

    private static final String ARIAL = "Arial";
    private static final String SEPARATOR_DATE_TIME = " ";
    private static final String SEPARATOR_DATE = "/";
    private static final String SEPARATOR = ":";
    private static final int SECOND_MILLIS = 1000;
//    private static final Color COLOR = GuiConstants.newColor(0,0,255);
    private static final int DEFAULT_NB_CHARS = 10;
    private static final int HEIGHT_TIME = 15;

    private final AbsTextField component;

    public Clock(AbstractProgramInfos _fact) {
        component = _fact.getCompoFactory().newTextField(DEFAULT_NB_CHARS);
        component.setEditable(false);
        component.setFont(ARIAL,GuiConstants.PLAIN,HEIGHT_TIME);
        component.setForeground(GuiConstants.newColor(0,0,255));
        AbstractScheduledExecutorService timer_ = _fact.getThreadFactory().newScheduledExecutorService();
        timer_.scheduleAtFixedRate(new UpdateTimeEvent(_fact.getThreadFactory(),this),0,SECOND_MILLIS);
//        timer = new Timer(SECOND_MILLIS, new UpdateTimeEvent(this));
//        timer.start();
    }

    public void setTimeText(AbstractThreadFactory _fact) {
        component.setText(getTimeText(_fact));
    }

    public static String getDateTimeText(String _separatorDate, String _sep, String _separatorTime, AbstractThreadFactory _fact) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_,date(_separatorDate) + _sep + time(_separatorTime));
    }

    public static String getDateTimeText(AbstractThreadFactory _fact) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_,date(SEPARATOR_DATE) + SEPARATOR_DATE_TIME + time(SEPARATOR));
    }

    public static String getTimeText(AbstractThreadFactory _fact) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_,time(SEPARATOR));
    }

    private static String date(String _separatorDate) {
        return "yyyy" + _separatorDate + "MM" + _separatorDate + "dd";
    }

    private static String time(String _separator) {
        return "HH" + _separator + "mm" + _separator + "ss";
    }

    public AbsCustComponent getComponent() {
        return component;
    }
}
