package code.gui.document;

import aiki.beans.*;
import aiki.game.*;
import code.gui.*;

public final class DefBeanChgUsesOfMove extends BeanChgUsesOfMove {
    private final GeneComponentModelLong use;
    private final GeneComponentModelLong max;
    public DefBeanChgUsesOfMove(GeneComponentModelLong _u, GeneComponentModelLong _m) {
        use = _u;
        max = _m;
    }
    @Override
    public UsesOfMove valueUse() {
        return new UsesOfMove(use.valueLong(),max.valueLong());
    }

    @Override
    public void valueUse(UsesOfMove _v) {
        use.valueLong(_v.getCurrent());
        max.valueLong(_v.getMax());
    }

}
