package code.util.ints;

public interface NumericableString<T> {

    void evaluateExp(boolean _checkSyntax);

    T getResult();

    boolean isValid();

    String beforeEvaluated();
}
