package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;

public final class ExecStaticFctContent {

    private final String methodName;

    private final ExecStaticEltContent elts;

    private final String lastType;

    private final int naturalVararg;

    public ExecStaticFctContent(AnaTypeFct _fct, AnaCallFctContent _a, Forwards _fwd) {
        methodName = _a.getMethodName();
        elts = new ExecStaticEltContent(_fct,_a,_fwd);
        lastType = _a.getLastType();
        naturalVararg = _a.getNaturalVararg();
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

    public ExecFormattedRootBlock getFormattedType() {
        return elts.getFormattedType();
    }

    public MethodAccessKind getKind() {
        return elts.getKind();
    }
}
