package code.expressionlanguage.methods.util;

public final class EqualsEl {

    private final String equalsEl;

    private final String firstArg;

    private final String secondArg;

    public EqualsEl(String _equalsEl, String _firstArg, String _secondArg) {
        equalsEl = _equalsEl;
        firstArg = _firstArg;
        secondArg = _secondArg;
    }

    public String getEqualsEl() {
        return equalsEl;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String getSecondArg() {
        return secondArg;
    }
}
