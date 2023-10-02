package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.gui.CrudGeneForm;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Ints;

public final class ValuePrefRendEvent implements AbsActionListener {
    private final CrudGeneForm<String,Integer> crud;
    private final CustList<RenderPointPair> candidates;

    public ValuePrefRendEvent(CrudGeneForm<String, Integer> _c, CustList<RenderPointPair> _bpc) {
        this.crud = _c;
        this.candidates = _bpc;
    }

    @Override
    public void action() {
        if (!crud.isVisibleSingle()) {
            return;
        }
        String cl_ = crud.getGeneKey().value();
        crud.getGeneValue().value(prefBpc(candidates, cl_));
    }

    public static int prefBpc(CustList<RenderPointPair> _p, String _cl) {
        Ints values_ = new Ints();
        for (RenderPointPair m: _p) {
            for (EntryCust<String,Integer> e: m.getPrefs().entryList()) {
                BreakPointBlockList.filter(_cl,values_,e);
            }
        }
        return BreakPointBlockList.pref(values_);
    }
}
