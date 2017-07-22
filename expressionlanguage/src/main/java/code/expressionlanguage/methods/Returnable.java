package code.expressionlanguage.methods;


public interface Returnable extends FunctionBlock, AccessibleBlock {

    String getReturnType();
    boolean isVarargs();
    String getName();
}
