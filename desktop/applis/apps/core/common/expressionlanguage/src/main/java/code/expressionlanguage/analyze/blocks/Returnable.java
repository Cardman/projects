package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.common.GeneFunction;
import code.util.StringList;


public interface Returnable extends FunctionBlock, GeneFunction {

    StringList getParametersNames();

}
