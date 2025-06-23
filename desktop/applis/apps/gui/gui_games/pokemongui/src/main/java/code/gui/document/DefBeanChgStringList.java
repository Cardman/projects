package code.gui.document;

import aiki.beans.*;
import code.gui.*;
import code.util.*;

public final class DefBeanChgStringList extends BeanChgStringList {
    private final GeneComponentModelLs<String> list;
    public DefBeanChgStringList(GeneComponentModelLs<String> _c) {
        list = _c;
    }
    @Override
    public StringList tryRet() {
        return new StringList(list.tryRet());
    }

    @Override
    public void setupValue(StringList _v) {
        super.setupValue(_v);
        list.setupValue(_v);
    }
}
