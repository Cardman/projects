package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;

public final class AnaCallFctContent {

    private final String methodName;

    private ClassMethodId classMethodId;

    private String lastType = "";

    private int naturalVararg = -1;
    private MemberId memberId = new MemberId();
    private AnaFormattedRootBlock formattedType;
    private AnaTypeFct function;

    public AnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public void update(ClassMethodIdReturn _res) {
        String foundClass_ = _res.getRealClass();
        MethodId realId_ = _res.getRealId();
        formattedType = _res.getFormattedType();
        memberId = _res.getMemberId();
        setClassMethodId(new ClassMethodId(foundClass_, realId_));
        function = _res.getPair();
        if (_res.isVarArgToCall()) {
            naturalVararg = realId_.getParametersTypesLength() - 1;
            lastType = realId_.getParametersType(naturalVararg);
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

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public AnaTypeFct getFunction() {
        return function;
    }
}
