package code.expressionlanguage.gui.unit;

public final class ResTestRow {
    private int number;
    private String method="";
    private String methodParams="";
    private String errMess="";
    private String resultSuccess="";
    private String resultSuccessLong="";
    private long time;
    private boolean success;

    public int getNumber() {
        return number;
    }

    public void setNumber(int _n) {
        this.number = _n;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String _m) {
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

    public String getResultSuccess() {
        return resultSuccess;
    }

    public void setResultSuccess(String _r) {
        this.resultSuccess = _r;
    }

    public String getResultSuccessLong() {
        return resultSuccessLong;
    }

    public void setResultSuccessLong(String _r) {
        this.resultSuccessLong = _r;
    }
}
