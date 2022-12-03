package code.bean.nat.exec;

import code.expressionlanguage.structs.Struct;

public final class NatArgumentsPair {

    private Struct argument;
    private Struct previousArgument;
    public Struct getArgument() {
        return argument;
    }
    public void setArgument(Struct _argument) {
        argument = _argument;
    }

    public Struct getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(Struct _previousArgument) {
        previousArgument = _previousArgument;
    }

}
