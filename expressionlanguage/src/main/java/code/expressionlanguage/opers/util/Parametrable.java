package code.expressionlanguage.opers.util;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    Class<?> getDeclaringClass();
}
