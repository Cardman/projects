package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterIncludeInstance implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        _bp.getResultInstance().getInclude().clear();
        _bp.getResultInstance().getInclude().addAllElts(_newValue);
    }

}
