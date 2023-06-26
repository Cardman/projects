package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterExcludeStatic implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        ExecFileBlockTraceIndex.setAll(_bp.getResultStatic().getExclude(),_newValue);
    }

}
