package aiki.beans;

import aiki.db.*;

public class BeanChgString implements IntBeanChgString {
    private String value = DataBase.EMPTY_STRING;

    @Override
    public String genericValue() {
        return tryRet();
    }

    @Override
    public String tryRet() {
        return value;
    }

    @Override
    public void setupValue(String _v) {
        value = _v;
    }
}
