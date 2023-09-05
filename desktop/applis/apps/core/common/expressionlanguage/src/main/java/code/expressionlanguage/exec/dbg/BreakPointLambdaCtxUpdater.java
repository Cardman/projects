package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;

public interface BreakPointLambdaCtxUpdater {
    ReportedMessages update(BreakPointBlockPair _bp, String _newValue);
}
