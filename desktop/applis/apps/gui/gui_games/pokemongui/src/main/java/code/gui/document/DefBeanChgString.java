package code.gui.document;

import code.gui.*;

public final class DefBeanChgString implements IntBeanChgString{
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
        text.setupValue(_v);
    }
}
