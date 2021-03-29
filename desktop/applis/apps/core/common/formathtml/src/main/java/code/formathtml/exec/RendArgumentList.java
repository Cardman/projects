package code.formathtml.exec;

import code.expressionlanguage.exec.util.ArgumentListCall;

public final class RendArgumentList {
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
