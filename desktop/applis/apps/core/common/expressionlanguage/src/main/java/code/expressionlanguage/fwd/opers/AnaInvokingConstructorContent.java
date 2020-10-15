package code.expressionlanguage.fwd.opers;

public final class AnaInvokingConstructorContent {
    private String classFromName = "";

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

    public String getClassFromName() {
        return classFromName;
    }

    public void setClassFromName(String _classFromName) {
        this.classFromName = _classFromName;
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public void setOffsetOper(int _offsetOper) {
        this.offsetOper = _offsetOper;
    }
}
