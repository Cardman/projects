package code.gui.document;

import code.gui.*;
import code.maths.*;

public final class DefBeanChgRate implements IntBeanChgRate{
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
