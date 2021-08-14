package code.sys.impl;

import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;

public final class DefaultDateFactory implements AbstractDateFactory {
    @Override
    public AbstractDate newDate(long _millis) {
        return new DefaultDate(_millis);
    }
}
