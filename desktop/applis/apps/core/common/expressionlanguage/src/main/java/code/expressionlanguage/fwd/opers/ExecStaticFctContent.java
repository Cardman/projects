package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;

public final class ExecStaticFctContent {


    private final ExecStaticFctCommonContent common;
    private final ExecStaticEltContent elts;

    public ExecStaticFctContent(AnaCallFctContent _a, Forwards _fwd) {
        common = new ExecStaticFctCommonContent(_a);
        elts = new ExecStaticEltContent(_a,_fwd);
    }

    public int getNaturalVararg() {
        return common.getNaturalVararg();
    }

    public String getLastType() {
        return common.getLastType();
    }

    public String getMethodName() {
        return common.getMethodName();
    }

    public ExecFormattedRootBlock getFormattedType() {
        return elts.getFormattedType();
    }

    public MethodAccessKind getKind() {
        return elts.getKind();
    }
}
