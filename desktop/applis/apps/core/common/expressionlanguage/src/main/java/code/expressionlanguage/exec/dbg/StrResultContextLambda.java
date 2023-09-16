package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.ResultContextLambda;

public final class StrResultContextLambda {
    private ResultContextLambda result;
    private String resultStr = "";
    private ReportedMessages reportedMessages = new ReportedMessages();

    public void result(ResultContextLambda _p, String _str) {
        reportedMessages = _p.getReportedMessages();
        setResult(ResultContextLambda.okOrNull(_p));
        setResultStr(ResultContextLambda.okOrEmpty(_p,_str));
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

    public ResultContextLambda getResult() {
        return result;
    }

    public void setResult(ResultContextLambda _p) {
        this.result = _p;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String _p) {
        this.resultStr = _p;
    }
}
