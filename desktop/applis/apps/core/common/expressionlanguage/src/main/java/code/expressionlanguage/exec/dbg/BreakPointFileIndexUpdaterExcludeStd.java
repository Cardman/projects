package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterExcludeStd implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        ExecFileBlockTraceIndex.setAll(_bp.getResultStd().getExclude(),_newValue);
    }

}
