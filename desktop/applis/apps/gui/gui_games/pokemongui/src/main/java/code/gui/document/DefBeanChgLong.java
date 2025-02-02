package code.gui.document;

import code.gui.*;

public final class DefBeanChgLong implements IntBeanChgLong{
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
