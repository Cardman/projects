package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;

public final class BreakPointLambdaCtxUpdaterStatic implements BreakPointLambdaCtxUpdater {
    private final ResultContext result;
    private final AbsLightContextGenerator gene;
    public BreakPointLambdaCtxUpdaterStatic(ResultContext _result, AbsLightContextGenerator _gene) {
        result = _result;
        gene = _gene;
    }

    @Override
    public ReportedMessages update(BreakPointBlockPair _bp, String _newValue) {
        _bp.getValue().getResultStatic().analyze(_bp,_newValue,"", "", result,gene);
        return _bp.getValue().getResultStatic().lda().getReportedMessages();
    }

}
