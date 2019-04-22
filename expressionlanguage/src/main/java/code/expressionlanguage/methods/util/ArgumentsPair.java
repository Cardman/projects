package code.expressionlanguage.methods.util;
import code.expressionlanguage.Argument;

public class ArgumentsPair {

    private Argument argument;
    private Argument previousArgument;
    public Argument getArgument() {
        return argument;
    }
    public void setArgument(Argument _argument) {
        argument = _argument;
    }
    public Argument getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

}
