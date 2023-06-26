package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class BreakPointFileIndexUpdaterIncludeStatic implements BreakPointFileIndexUpdater {

    @Override
    public void update(BreakPoint _bp, CustList<ExecFileBlockTraceIndex> _newValue) {
        ExecFileBlockTraceIndex.setAll(_bp.getResultStatic().getInclude(),_newValue);
    }

}
