package code.expressionlanguage.methods;

import code.expressionlanguage.stds.LgNames;
import code.util.StringList;


public interface Returnable extends FunctionBlock, AccessibleBlock {

    String getSignature();
    String getReturnType(LgNames _stds);
    boolean isVarargs();
    String getName();
    StringList getParametersNames();
    StringList getParametersTypes();

}
