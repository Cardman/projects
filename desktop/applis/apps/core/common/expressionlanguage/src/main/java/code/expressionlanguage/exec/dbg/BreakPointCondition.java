package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;
import code.util.CustList;

public final class BreakPointCondition {
    private ResultContextLambda result;
    private String resultStr = "";
    private int countModulo;
    private int count;
    private final CustList<ExecFileBlockTraceIndex> exclude = new CustList<ExecFileBlockTraceIndex>();
    private final CustList<ExecFileBlockTraceIndex> include = new CustList<ExecFileBlockTraceIndex>();

    public CustList<ExecFileBlockTraceIndex> getExclude() {
        return exclude;
    }

    public CustList<ExecFileBlockTraceIndex> getInclude() {
        return include;
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

    public int getCountModulo() {
        return countModulo;
    }

    public void setCountModulo(int _p) {
        this.countModulo = _p;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int _p) {
        this.count = _p;
    }
}
