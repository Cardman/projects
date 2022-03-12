package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;

public final class ExecStaticFctContent {


    private final ExecStaticFctCommonContent common;
    private final ExecStaticEltContent elts;

    public ExecStaticFctContent(AnaCallFctContent _a, Forwards _fwd) {
        this(new ExecStaticFctCommonContent(_a),new ExecStaticEltContent(_a,_fwd));
    }
    private ExecStaticFctContent(ExecStaticFctCommonContent _c, ExecStaticEltContent _e) {
        common = _c;
        elts = _e;
    }
    public static ExecStaticFctContent initByNotNull(AnaCallFctContent _a, Forwards _fwd) {
        return new ExecStaticFctContent(new ExecStaticFctCommonContent(_a),ExecStaticEltContent.initByNotNull(_a, _fwd));
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

    public ExecStaticEltContent getElts() {
        return elts;
    }

    public MethodAccessKind getKind() {
        return elts.getKind();
    }
}
