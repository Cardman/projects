package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneFunction;
import code.util.StringList;


public interface Returnable extends FunctionBlock, GeneFunction {

    String getSignature(Analyzable _an);
    StringList getParametersNames();

}
