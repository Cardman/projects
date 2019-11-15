package code.expressionlanguage.common;

import code.expressionlanguage.opers.util.MethodId;


public interface GeneMethod extends GeneFunction {

    MethodId getId();

    boolean hiddenInstance();
    boolean isStaticMethod();

    boolean isFinalMethod();

    boolean isAbstractMethod();

}
