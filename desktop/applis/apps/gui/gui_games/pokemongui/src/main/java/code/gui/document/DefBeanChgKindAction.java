package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.actions.*;
import code.gui.*;

public final class DefBeanChgKindAction extends BeanChgKindAction {
    private final GeneComponentModelElt<KindAction> check;
    public DefBeanChgKindAction(GeneComponentModelElt<KindAction> _ch) {
        check = _ch;
    }

    @Override
    public KindAction valueKa() {
        return check.tryRet();
    }

    @Override
    public void valueKa(KindAction _v) {
        check.setupValue(_v);
    }

}
