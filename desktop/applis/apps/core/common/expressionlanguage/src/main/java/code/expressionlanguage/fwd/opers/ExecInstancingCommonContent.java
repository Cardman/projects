package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInstancingCommonContent {
    private final String methodName;

    private final ExecFormattedRootBlock formattedType;

    private final int naturalVararg;

    private final String lastType;

    private final ConstructorId constId;

    public ExecInstancingCommonContent(AnaInstancingCommonContent _cont, Forwards _fwd) {
        this(_cont, FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd));
    }
    public ExecInstancingCommonContent(AnaInstancingCommonContent _cont, ExecFormattedRootBlock _formattedType) {
        methodName = _cont.getMethodName();
        formattedType = _formattedType;
        naturalVararg = _cont.getNaturalVararg();
        lastType = _cont.getLastType();
        constId = _cont.getConstId();
    }
    public String getMethodName() {
        return methodName;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public ConstructorId getConstId() {
        return constId;
    }
}
