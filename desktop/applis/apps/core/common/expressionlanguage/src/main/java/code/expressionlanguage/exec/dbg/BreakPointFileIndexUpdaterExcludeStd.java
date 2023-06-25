package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterExcludeStd implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        _bp.getResultStd().getExclude().clear();
        _bp.getResultStd().getExclude().addAllElts(_newValue);
    }

}
