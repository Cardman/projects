package aiki.beans;

public class BeanChgLong implements IntBeanChgLong {

    private long value;

    @Override
    public Long genericValue() {
        return valueLong();
    }

    @Override
    public long valueLong() {
        return value;
    }

    @Override
    public void valueLong(long _v) {
        value = _v;
    }
}
