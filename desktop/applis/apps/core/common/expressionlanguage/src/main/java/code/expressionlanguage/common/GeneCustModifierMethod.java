package code.expressionlanguage.common;


public interface GeneCustModifierMethod extends GeneCustMethod {

    boolean isFinalMethod();

    boolean isAbstractMethod();
    String getImportedReturnType();
}
