package code.expressionlanguage.common;


public interface GeneCustModifierMethod extends GeneCustMethod,GeneCustStaticMethod {

    boolean isFinalMethod();
    boolean hiddenInstance();

    boolean isAbstractMethod();
}
