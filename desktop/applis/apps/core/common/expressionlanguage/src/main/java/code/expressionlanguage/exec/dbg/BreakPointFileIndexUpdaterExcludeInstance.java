package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterExcludeInstance implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        _bp.getResultInstance().getExclude().clear();
        _bp.getResultInstance().getExclude().addAllElts(_newValue);
    }

}
