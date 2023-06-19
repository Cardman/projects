package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;

public final class BreakPoint {
    private boolean enabled;
    private boolean enabledChgtType;
    private boolean instanceType = true;
    private boolean staticType;
    private ResultContextLambda resultStd;
    private ResultContextLambda resultStatic;
    private ResultContextLambda resultInstance;
    private String resultStrStd = "";
    private String resultStrStatic = "";
    private String resultStrInstance = "";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isEnabledChgtType() {
        return enabledChgtType;
    }

    public void setEnabledChgtType(boolean _e) {
        this.enabledChgtType = _e;
    }

    public boolean isInstanceType() {
        return instanceType;
    }

    public void setInstanceType(boolean _i) {
        this.instanceType = _i;
    }

    public boolean isStaticType() {
        return staticType;
    }

    public void setStaticType(boolean _s) {
        this.staticType = _s;
    }

    public ResultContextLambda getResultStd() {
        return resultStd;
    }

    public void setResultStd(ResultContextLambda _p) {
        this.resultStd = _p;
    }

    public ResultContextLambda getResultStatic() {
        return resultStatic;
    }

    public void setResultStatic(ResultContextLambda _p) {
        this.resultStatic = _p;
    }

    public ResultContextLambda getResultInstance() {
        return resultInstance;
    }

    public void setResultInstance(ResultContextLambda _p) {
        this.resultInstance = _p;
    }

    public String getResultStrStd() {
        return resultStrStd;
    }

    public void setResultStrStd(String _p) {
        this.resultStrStd = _p;
    }

    public String getResultStrStatic() {
        return resultStrStatic;
    }

    public void setResultStrStatic(String _p) {
        this.resultStrStatic = _p;
    }

    public String getResultStrInstance() {
        return resultStrInstance;
    }

    public void setResultStrInstance(String _p) {
        this.resultStrInstance = _p;
    }
}