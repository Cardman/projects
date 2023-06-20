package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;

public final class BreakPointLambdaCtxUpdaterStd implements BreakPointLambdaCtxUpdater {
    private final ResultContext result;
    private final AbsContextGenerator gene;
    public BreakPointLambdaCtxUpdaterStd(ResultContext _result, AbsContextGenerator _gene) {
        result = _result;
        gene = _gene;
    }

    @Override
    public ReportedMessages update(String _fileName, int _caret, BreakPoint _bp, String _newValue) {
        String type_ = result.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, _fileName, _caret, result, type_, gene, null);
        _bp.getResultStd().setResult(ResultContextLambda.okOrNull(res_));
        _bp.getResultStd().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
        return ResultContextLambda.after(res_);
    }

}
