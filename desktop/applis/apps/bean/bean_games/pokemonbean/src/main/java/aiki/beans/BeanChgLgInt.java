package aiki.beans;

import code.maths.*;

public class BeanChgLgInt implements IntBeanChgLgInt {

    private LgInt value = LgInt.zero();
    @Override
    public LgInt valueLgInt() {
        return value;
    }

    @Override
    public void valueLgInt(LgInt _v) {
        value = _v;
    }
}
