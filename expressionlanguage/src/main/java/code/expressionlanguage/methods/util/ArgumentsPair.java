package code.expressionlanguage.methods.util;
import code.expressionlanguage.Argument;

public class ArgumentsPair {

    private Argument argument;
    private Argument previousArgument;
    private boolean convertToString;
    private boolean calledToString;
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

    public boolean isConvertToString() {
        return convertToString;
    }

    public void setConvertToString(boolean _convertToString) {
        convertToString = _convertToString;
    }

    public boolean isCalledToString() {
        return calledToString;
    }

    public void setCalledToString(boolean _calledToString) {
        calledToString = _calledToString;
    }
}
