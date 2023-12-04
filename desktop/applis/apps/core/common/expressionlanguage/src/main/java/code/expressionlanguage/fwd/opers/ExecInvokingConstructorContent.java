package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;

public final class ExecInvokingConstructorContent {
    private final ExecFormattedRootBlock formattedType;

    private final String lastType;

    private final int naturalVararg;
    private final int offsetOper;

    public ExecInvokingConstructorContent(AnaInvokingConstructorContent _cont, Forwards _fwd) {
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
        offsetOper = _cont.getOffsetOper();
        formattedType = ExecStaticEltContent.build(_cont.getFormattedType(),_fwd);
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
