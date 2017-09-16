package code.expressionlanguage.methods.util;

public final class EqualsEl {

    private final String firstArg;

    private final String secondArg;

    public EqualsEl(String _firstArg, String _secondArg) {
        firstArg = _firstArg;
        secondArg = _secondArg;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String getSecondArg() {
        return secondArg;
    }
}
