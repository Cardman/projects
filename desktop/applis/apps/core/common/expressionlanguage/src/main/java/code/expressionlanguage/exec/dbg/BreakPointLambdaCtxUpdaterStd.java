package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;

public final class BreakPointLambdaCtxUpdaterStd implements BreakPointLambdaCtxUpdater {
    private final ResultContext result;
    private final AbsLightContextGenerator gene;
    public BreakPointLambdaCtxUpdaterStd(ResultContext _result, AbsLightContextGenerator _gene) {
        result = _result;
        gene = _gene;
    }

    @Override
    public ReportedMessages update(String _fileName, int _caret, BreakPoint _bp, String _newValue) {
        String type_ = result.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, _fileName, _caret, result, type_, gene, null);
        _bp.getResultStd().result(res_,_newValue);
        return ResultContextLambda.after(res_);
    }

}
