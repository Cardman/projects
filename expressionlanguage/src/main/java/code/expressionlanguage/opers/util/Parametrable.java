package code.expressionlanguage.opers.util;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    String getClassName();

    boolean isStatic();
}
