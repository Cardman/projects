package code.expressionlanguage.common;

import code.expressionlanguage.opers.util.MethodId;


public interface GeneMethod extends GeneFunction {

    MethodId getId();

    boolean isStaticMethod();

}
