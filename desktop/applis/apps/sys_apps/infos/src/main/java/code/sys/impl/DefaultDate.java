package code.sys.impl;

import code.threads.AbstractDate;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DefaultDate implements AbstractDate {
    private final Date date;

    public DefaultDate(long _millis) {
        date = new Date(_millis);
    }

    @Override
    public String format(String _format) {
        return new SimpleDateFormat(_format).format(date);
    }
}
