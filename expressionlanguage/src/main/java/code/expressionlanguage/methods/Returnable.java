package code.expressionlanguage.methods;

import code.expressionlanguage.opers.util.FctConstraints;
import code.util.StringList;


public interface Returnable extends FunctionBlock, AccessibleBlock {

    String getReturnType();
    boolean isVarargs();
    String getName();
    FctConstraints getConstraints(Classes _classes);
    StringList getParametersNames();
    StringList getParametersTypes();

}
