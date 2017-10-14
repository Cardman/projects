package code.expressionlanguage.methods;

import code.util.StringList;


public interface Returnable extends FunctionBlock, AccessibleBlock {

    String getReturnType();
    boolean isVarargs();
    String getName();
    StringList getParametersNames();
    StringList getParametersTypes();

}
