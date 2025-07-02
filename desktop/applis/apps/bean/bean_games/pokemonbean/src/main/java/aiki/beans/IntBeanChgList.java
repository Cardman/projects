package aiki.beans;

import code.util.*;

public interface IntBeanChgList<T> extends IntBeanChgValue<CustList<T>> {
    CustList<T> tryRet();
    void setupValue(CustList<T> _v);
}
