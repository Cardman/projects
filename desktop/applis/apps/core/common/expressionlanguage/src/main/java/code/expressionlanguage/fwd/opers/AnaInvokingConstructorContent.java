package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public final class AnaInvokingConstructorContent {
    private AnaFormattedRootBlock formattedType;

    private String lastType = "";

    private int naturalVararg = -1;
    private int offsetOper;

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String _lastType) {
        this.lastType = _lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int _naturalVararg) {
        this.naturalVararg = _naturalVararg;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public void setOffsetOper(int _offsetOper) {
        this.offsetOper = _offsetOper;
    }
}
