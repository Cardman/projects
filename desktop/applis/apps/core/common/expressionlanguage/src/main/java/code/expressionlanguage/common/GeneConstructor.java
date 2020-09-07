package code.expressionlanguage.common;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;


public interface GeneConstructor {

    ConstructorId getId();
    StringList getParametersNames();
}
