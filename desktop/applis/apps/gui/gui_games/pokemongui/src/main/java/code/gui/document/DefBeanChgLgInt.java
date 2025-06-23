package code.gui.document;

import aiki.beans.*;
import code.gui.*;
import code.maths.*;

public final class DefBeanChgLgInt extends BeanChgLgInt {
    private final GeneComponentModelLgInt text;
    public DefBeanChgLgInt(GeneComponentModelLgInt _c) {
        text = _c;
    }
    @Override
    public LgInt valueLgInt() {
        return text.valueLgInt();
    }

    @Override
    public void valueLgInt(LgInt _v) {
        text.valueLgInt(_v);
    }

}
