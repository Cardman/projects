package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public final class AnaCallFctContent {

    private final String methodName;

    private ClassMethodId classMethodId;

    private String lastType = "";

    private int naturalVararg = -1;
    private MemberId memberId = new MemberId();
    private AnaFormattedRootBlock formattedType;

    public AnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public void update(ClassMethodIdReturn _res) {
        String foundClass_ = _res.getRealClass();
        MethodId realId_ = _res.getRealId();
        setFormattedType(_res.getFormattedType());
        setMemberId(_res.getMemberId());
        setClassMethodId(new ClassMethodId(foundClass_, realId_));
        if (_res.isVarArgToCall()) {
            StringList paramtTypes_ = realId_.getParametersTypes();
            setNaturalVararg(paramtTypes_.size() - 1);
            setLastType(paramtTypes_.last());
        }
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

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
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
