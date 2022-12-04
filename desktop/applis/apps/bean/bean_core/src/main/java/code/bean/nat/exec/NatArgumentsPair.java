package code.bean.nat.exec;

import code.bean.nat.*;

public final class NatArgumentsPair {

    private NaSt argument;
    private NaSt previousArgument;
    public NaSt getArgument() {
        return argument;
    }
    public void setArgument(NaSt _argument) {
        argument = _argument;
    }

    public NaSt getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(NaSt _previousArgument) {
        previousArgument = _previousArgument;
    }

}
