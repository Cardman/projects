package code.expressionlanguage.gui.unit;

import code.expressionlanguage.structs.MethodMetaInfo;

public final class ResTestRow {
    private int number;
    private MethodMetaInfo method=new MethodMetaInfo();
    private String methodParams="";
    private String errMess="";
    private long time;
    private boolean success;

    public int getNumber() {
        return number;
    }

    public void setNumber(int _n) {
        this.number = _n;
    }

    public MethodMetaInfo getMethod() {
        return method;
    }

    public void setMethod(MethodMetaInfo _m) {
        this.method = _m;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String _m) {
        this.methodParams = _m;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean _s) {
        this.success = _s;
    }

    public String getErrMess() {
        return errMess;
    }

    public void setErrMess(String _e) {
        this.errMess = _e;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long _t) {
        this.time = _t;
    }
}
