package code.gui.document;

import aiki.beans.*;
import code.gui.*;
import code.maths.*;

public final class DefBeanChgRate extends BeanChgRate {
    private final GeneComponentModelRate text;
    public DefBeanChgRate(GeneComponentModelRate _c) {
        text = _c;
    }
    @Override
    public Rate valueRate() {
        return text.valueRate();
    }

    @Override
    public void valueRate(Rate _v) {
        text.valueRate(_v);
    }

}
