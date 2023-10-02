package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.gui.CrudGeneForm;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ValuePrefEvent implements AbsActionListener {
    private final CrudGeneForm<String,Integer> crud;
    private final CustList<BreakPointCondition> candidates;

    public ValuePrefEvent(CrudGeneForm<String, Integer> _c, CustList<BreakPointCondition> _bpc) {
        this.crud = _c;
        this.candidates = _bpc;
    }

    @Override
    public void action() {
        if (!crud.isVisibleSingle()) {
            return;
        }
        String cl_ = crud.getGeneKey().value();
        crud.getGeneValue().value(BreakPointBlockList.prefBpc(candidates, cl_));
    }
}
