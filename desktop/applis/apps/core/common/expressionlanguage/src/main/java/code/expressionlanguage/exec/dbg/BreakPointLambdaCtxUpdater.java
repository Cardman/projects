package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;

public interface BreakPointLambdaCtxUpdater {
    ReportedMessages update(String _fileName, int _caret, BreakPoint _bp, String _newValue);
}
