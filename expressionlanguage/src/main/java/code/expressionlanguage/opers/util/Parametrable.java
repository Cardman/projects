package code.expressionlanguage.opers.util;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    Identifiable getId();

    String getClassName();

    boolean isStatic();

    boolean isVararg();
}
