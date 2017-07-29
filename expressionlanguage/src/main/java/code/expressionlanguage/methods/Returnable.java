package code.expressionlanguage.methods;

import code.expressionlanguage.opers.util.FctConstraints;


public interface Returnable extends FunctionBlock, AccessibleBlock {

    String getReturnType();
    boolean isVarargs();
    String getName();
    FctConstraints getConstraints();

    void setConstraints(FctConstraints _constraints);
}
