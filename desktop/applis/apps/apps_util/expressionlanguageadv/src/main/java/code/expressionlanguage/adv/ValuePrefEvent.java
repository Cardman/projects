package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.options.ResultContext;
import code.gui.CrudGeneForm;
import code.gui.events.AbsActionListener;

public final class ValuePrefEvent implements AbsActionListener {
    private final CrudGeneForm<String,Integer> crud;
    private final ResultContext resultContext;
    private final boolean exit;

    public ValuePrefEvent(CrudGeneForm<String,Integer> _c, ResultContext _res, boolean _e) {
        this.crud = _c;
        this.resultContext = _res;
        this.exit = _e;
    }

    @Override
    public void action() {
        if (!crud.isVisibleSingle()) {
            return;
        }
        String cl_ = crud.getGeneKey().value();
        crud.getGeneValue().value(BreakPointBlockList.pref(resultContext.getContext().metList(), exit, cl_));
    }
}
