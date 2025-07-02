package code.gui.document;

import aiki.beans.*;
import code.gui.*;
import code.util.*;

public final class DefBeanChgList<T> extends BeanChgList<T> {
    private final GeneComponentModelLs<T> list;
    public DefBeanChgList(GeneComponentModelLs<T> _c) {
        list = _c;
    }
    @Override
    public CustList<T> tryRet() {
        return list.tryRet();
    }

    @Override
    public void setupValue(CustList<T> _v) {
        list.setupValue(_v);
    }
}
