package code.expressionlanguage.fwd.opers;

public final class ExecStaticFctCommonContent {

    private final String methodName;

    private final String lastType;

    private final int naturalVararg;

    public ExecStaticFctCommonContent(AnaCallFctContent _a) {
        this(_a.getMethodName(),_a.getLastType(), _a.getNaturalVararg());
    }
    public ExecStaticFctCommonContent(String _meth, String _last, int _natural){
        methodName = _meth;
        lastType = _last;
        naturalVararg = _natural;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public String getMethodName() {
        return methodName;
    }
}
