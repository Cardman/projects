package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.gui.*;
import code.gui.events.*;
import code.util.*;

public final class ValuePrefEvent implements AbsActionListener {
    private final CrudGeneForm crud;
    private final CustList<BreakPointCondition> candidates;

    public ValuePrefEvent(CrudGeneForm _c, CustList<BreakPointCondition> _bpc) {
        this.crud = _c;
        this.candidates = _bpc;
    }

    @Override
    public void action() {
        if (!crud.isVisibleSingle()) {
            return;
        }
        String cl_ = crud.getGeneComponentModelEntryStringInteger().getKey().valueString();
        crud.getGeneComponentModelEntryStringInteger().getValue().valueInt(BreakPointBlockList.prefBpc(candidates, cl_));
    }
}
