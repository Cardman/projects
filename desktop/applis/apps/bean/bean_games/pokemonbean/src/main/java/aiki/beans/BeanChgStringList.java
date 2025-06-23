package aiki.beans;

import code.util.*;

public class BeanChgStringList implements IntBeanChgStringList {
    private StringList value = new StringList();

    @Override
    public StringList tryRet() {
        return value;
    }

    @Override
    public void setupValue(StringList _v) {
        value = _v;
    }
}
