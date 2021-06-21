package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInstFctContent {

    private final int methodName;

    private final String lastType;

    private final int naturalVararg;

    private final int anc;

    private final boolean staticChoiceMethod;
    private final ExecFormattedRootBlock formattedType;
    public ExecInstFctContent(AnaCallFctContent _cont, int _anc, boolean _staticChoiceMethod, Forwards _fwd) {
        this(_anc,_staticChoiceMethod, StringExpUtil.getOffset(_cont.getMethodName()), _cont.getLastType(), _cont.getNaturalVararg(), FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd));
    }
    public ExecInstFctContent(ExecFormattedRootBlock _formattedType) {
        this(0, false, 0, "", -1, _formattedType);
    }
    public ExecInstFctContent(int _anc, boolean _staticChoiceMethod, int _methodName, String _lastType, int _naturalVararg, ExecFormattedRootBlock _formattedType) {
        methodName = _methodName;
        lastType = _lastType;
        naturalVararg = _naturalVararg;
        anc = _anc;
        staticChoiceMethod = _staticChoiceMethod;
        formattedType = _formattedType;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public int getMethodName() {
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
