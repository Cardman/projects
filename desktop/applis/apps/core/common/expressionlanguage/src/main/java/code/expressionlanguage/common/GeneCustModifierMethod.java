package code.expressionlanguage.common;


public interface GeneCustModifierMethod extends GeneCustMethod {

    boolean isFinalMethod();
    boolean hiddenInstance();

    boolean isAbstractMethod();
    String getImportedReturnType();
}
