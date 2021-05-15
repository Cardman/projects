package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInvokingConstructorContent {
    private final ExecFormattedRootBlock formattedType;

    private final String lastType;

    private final int naturalVararg;
    private final int offsetOper;

    public ExecInvokingConstructorContent(AnaInvokingConstructorContent _cont, Forwards _fwd) {
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
        offsetOper = _cont.getOffsetOper();
        formattedType = FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd);
    }
    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public int getOffsetOper() {
        return offsetOper;
    }
}
