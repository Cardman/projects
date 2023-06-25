package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterIncludeStd implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        _bp.getResultStd().getInclude().clear();
        _bp.getResultStd().getInclude().addAllElts(_newValue);
    }

}
