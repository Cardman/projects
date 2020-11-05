package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInstFctContent {

    private final String methodName;

    private final String className;

    private final String lastType;

    private final int naturalVararg;

    private final int anc;

    private final boolean staticChoiceMethod;
    public ExecInstFctContent(AnaCallFctContent _cont, int _anc, boolean _staticChoiceMethod) {
        methodName = _cont.getMethodName();
        className = FetchMemberUtil.getType(_cont.getClassMethodId());
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
        anc = _anc;
        staticChoiceMethod = _staticChoiceMethod;
    }
    public ExecInstFctContent(ClassMethodId _cl) {
        methodName = _cl.getConstraints().getName();
        className = FetchMemberUtil.getType(_cl);
        lastType = "";
        naturalVararg = -1;
        anc = 0;
        staticChoiceMethod = false;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public int getAnc() {
        return anc;
    }
}
