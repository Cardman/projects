package code.gui.document;

import aiki.beans.*;
import code.gui.*;

public final class DefBeanChgTxt extends BeanChgString {
    private final AbsTextField text;
    public DefBeanChgTxt(AbsTextField _c) {
        text = _c;
    }
    @Override
    public String tryRet() {
        return text.getText();
    }

    @Override
    public void setupValue(String _v) {
        text.setText(_v);
    }
}
