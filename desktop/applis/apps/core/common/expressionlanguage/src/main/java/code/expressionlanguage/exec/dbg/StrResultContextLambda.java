package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;

public final class StrResultContextLambda {
    private ResultContextLambda result;
    private String resultStr = "";
    public void result(ResultContextLambda _p, String _str) {
        setResult(ResultContextLambda.okOrNull(_p));
        setResultStr(ResultContextLambda.okOrEmpty(_p,_str));
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
