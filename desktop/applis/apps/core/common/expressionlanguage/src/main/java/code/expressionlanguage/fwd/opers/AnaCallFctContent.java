package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.functionid.ClassMethodId;

public final class AnaCallFctContent {

    private final String methodName;

    private ClassMethodId classMethodId;
    private String className = "";

    private String lastType = "";

    private int naturalVararg = -1;
    private MemberId memberId = new MemberId();

    public AnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public void setClassMethodId(ClassMethodId _classMethodId) {
        this.classMethodId = _classMethodId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String _lastType) {
        this.lastType = _lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int _naturalVararg) {
        this.naturalVararg = _naturalVararg;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

}
