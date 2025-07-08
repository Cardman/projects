package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.util.*;
import code.gui.*;

public final class DefBeanChgCopiedMove extends BeanChgCopiedMove {

    private final GeneComponentModelElt<String> name;
    private final GeneComponentModelLong spinner;

    public DefBeanChgCopiedMove(GeneComponentModelElt<String> _n, GeneComponentModelLong _s) {
        this.name = _n;
        this.spinner = _s;
    }

    @Override
    public CopiedMove valCp() {
        return new CopiedMove(name.tryRet(), spinner.valueLong());
    }

    @Override
    public void valCp(CopiedMove _v) {
        name.setupValue(_v.getMove());
        spinner.valueLong(_v.getPp());
    }

}
