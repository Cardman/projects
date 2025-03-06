package code.gui.document;

import aiki.beans.*;
import code.gui.*;

public final class DefBeanChgInt extends BeanChgInt {
    private final GeneComponentModelElt<Integer> spinner;
    public DefBeanChgInt(GeneComponentModelElt<Integer> _c) {
        spinner = _c;
    }
    @Override
    public int valueInt() {
        return spinner.tryRet();
    }

    @Override
    public void valueInt(int _v) {
        spinner.setupValue(_v);
    }

}
