package code.expressionlanguage.fwd.opers;

public final class ExecInvokingConstructorContent {
    private final String classFromName;

    private final String lastType;

    private final int naturalVararg;
    private final int offsetOper;

    public ExecInvokingConstructorContent(AnaInvokingConstructorContent _cont) {
        classFromName = _cont.getClassFromName();
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
        offsetOper = _cont.getOffsetOper();
    }
    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getClassFromName() {
        return classFromName;
    }

    public int getOffsetOper() {
        return offsetOper;
    }
}
