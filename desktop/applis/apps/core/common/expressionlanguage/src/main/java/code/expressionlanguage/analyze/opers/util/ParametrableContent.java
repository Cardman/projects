package code.expressionlanguage.analyze.opers.util;

public final class ParametrableContent {
    private boolean varArgToCall;
    private String fileName;
    private MemberId memberId = new MemberId();
    private AnaTypeFct pair;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _f) {
        this.fileName = _f;
    }

    public AnaTypeFct getPair() {
        return pair;
    }

    public void setPair(AnaTypeFct _p) {
        this.pair = _p;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _m) {
        this.memberId = _m;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _v) {
        this.varArgToCall = _v;
    }
}
