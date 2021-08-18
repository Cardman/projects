package code.sys.impl;

import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;

import java.util.TimeZone;

public final class DefaultDateFactory implements AbstractDateFactory {
    @Override
    public AbstractDate newDate(long _millis) {
        return new DefaultDate(_millis);
    }

    @Override
    public long timeZone(long _millis) {
        return TimeZone.getDefault().getOffset(_millis);
    }

}
