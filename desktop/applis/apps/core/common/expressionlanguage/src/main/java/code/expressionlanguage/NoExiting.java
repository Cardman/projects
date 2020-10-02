package code.expressionlanguage;

public final class NoExiting implements AbstractExiting {

    @Override
    public boolean hasToExit(String _className) {
        return false;
    }

    @Override
    public boolean hasToExit(String _className, Argument _arg) {
        return false;
    }
}
