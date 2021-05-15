package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInstFctContent {

    private final String methodName;

    private final String lastType;

    private final int naturalVararg;

    private final int anc;

    private final boolean staticChoiceMethod;
    private final ExecFormattedRootBlock formattedType;
    public ExecInstFctContent(AnaCallFctContent _cont, int _anc, boolean _staticChoiceMethod, Forwards _fwd) {
        methodName = _cont.getMethodName();
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
        anc = _anc;
        staticChoiceMethod = _staticChoiceMethod;
        formattedType = FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd);
    }
    public ExecInstFctContent(ClassMethodId _cl, ExecRootBlock _root) {
        methodName = _cl.getConstraints().getName();
        lastType = "";
        naturalVararg = -1;
        anc = 0;
        staticChoiceMethod = false;
        formattedType = new ExecFormattedRootBlock(_root,_cl.getClassName());
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
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
