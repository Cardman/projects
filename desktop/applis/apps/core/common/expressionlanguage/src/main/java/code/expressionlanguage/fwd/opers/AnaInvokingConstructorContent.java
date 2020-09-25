package code.expressionlanguage.fwd.opers;

public final class AnaInvokingConstructorContent {
    private String classFromName = "";

    private String lastType = "";

    private int naturalVararg = -1;
    private int offsetOper;

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int naturalVararg) {
        this.naturalVararg = naturalVararg;
    }

    public String getClassFromName() {
        return classFromName;
    }

    public void setClassFromName(String classFromName) {
        this.classFromName = classFromName;
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public void setOffsetOper(int offsetOper) {
        this.offsetOper = offsetOper;
    }
}
