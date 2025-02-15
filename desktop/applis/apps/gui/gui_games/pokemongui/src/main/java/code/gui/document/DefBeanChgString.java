package code.gui.document;

import aiki.beans.*;
import code.gui.*;

public final class DefBeanChgString extends BeanChgString {
    private final GeneComponentModelElt<String> text;
    public DefBeanChgString(GeneComponentModelElt<String> _c) {
        text = _c;
    }
    @Override
    public String tryRet() {
        return text.tryRet();
    }

    @Override
    public void setupValue(String _v) {
        super.setupValue(_v);
        text.setupValue(_v);
    }
}
