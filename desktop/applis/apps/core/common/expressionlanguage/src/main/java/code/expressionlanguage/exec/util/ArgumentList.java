package code.expressionlanguage.exec.util;

public final class ArgumentList {
    private final ArgumentListCall arguments = new ArgumentListCall();
    private int naturalVararg = -1;

    public ArgumentListCall getArguments() {
        return arguments;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int _naturalVararg) {
        naturalVararg=_naturalVararg;
    }
}
