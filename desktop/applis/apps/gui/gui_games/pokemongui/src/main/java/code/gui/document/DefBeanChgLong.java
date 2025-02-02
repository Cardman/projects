package code.gui.document;

import aiki.beans.*;
import code.gui.*;

public final class DefBeanChgLong extends BeanChgLong {
    private final GeneComponentModelLong spinner;
    public DefBeanChgLong(GeneComponentModelLong _c) {
        spinner = _c;
    }
    @Override
    public long valueLong() {
        return spinner.valueLong();
    }

    @Override
    public void valueLong(long _v) {
        spinner.valueLong(_v);
    }

}
