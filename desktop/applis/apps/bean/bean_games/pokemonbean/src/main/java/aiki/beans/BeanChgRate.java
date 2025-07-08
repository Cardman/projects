package aiki.beans;

import code.maths.*;

public class BeanChgRate implements IntBeanChgRate {

    private Rate value = Rate.zero();

    @Override
    public Rate genericValue() {
        return valueRate();
    }

    @Override
    public Rate valueRate() {
        return value;
    }

    @Override
    public void valueRate(Rate _v) {
        value = _v;
    }
}
