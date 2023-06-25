package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterIncludeStatic implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        _bp.getResultStatic().getInclude().clear();
        _bp.getResultStatic().getInclude().addAllElts(_newValue);
    }

}
