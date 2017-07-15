package code.expressionlanguage.methods;


public interface Returnable extends FunctionBlock {

    String getReturnType();
    boolean isVarargs();
    String getName();
}
