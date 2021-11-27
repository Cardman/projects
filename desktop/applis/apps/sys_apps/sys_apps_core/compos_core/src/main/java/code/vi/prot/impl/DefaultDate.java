package code.vi.prot.impl;

import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DefaultDate implements AbstractDate {
    private final Date date;

    public DefaultDate(long _millis) {
        date = new Date(_millis);
    }

    @Override
    public String format(AbstractDateFactory _offset, String _format) {
        return format(_offset.timeZone(date.getTime()),_format);
    }

    @Override
    public String format(long _offset, String _format) {
        return new SimpleDateFormat(_format).format(date);
    }
}
