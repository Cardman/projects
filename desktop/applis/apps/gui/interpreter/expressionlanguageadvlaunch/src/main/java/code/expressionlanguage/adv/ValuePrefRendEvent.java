package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.gui.*;
import code.gui.events.*;
import code.util.*;

public final class ValuePrefRendEvent implements AbsActionListener {
    private final CrudGeneForm crud;
    private final CustList<RenderPointPair> candidates;

    public ValuePrefRendEvent(CrudGeneForm _c, CustList<RenderPointPair> _bpc) {
        this.crud = _c;
        this.candidates = _bpc;
    }

    @Override
    public void action() {
        if (!crud.isVisibleSingle()) {
            return;
        }
        String cl_ = crud.getGeneComponentModelEntryStringInteger().getKey().valueString();
        crud.getGeneComponentModelEntryStringInteger().getValue().valueInt(prefBpc(candidates, cl_));
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
