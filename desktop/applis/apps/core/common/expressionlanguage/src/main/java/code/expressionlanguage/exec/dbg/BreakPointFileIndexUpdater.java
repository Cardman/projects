package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public interface BreakPointFileIndexUpdater {
    void update(BreakPoint _bp, CustList<AbsCallContraints> _newValue);
}
