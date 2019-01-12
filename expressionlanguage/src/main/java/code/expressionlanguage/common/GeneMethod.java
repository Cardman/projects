package code.expressionlanguage.common;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;


public interface GeneMethod extends GeneFunction {

    @Override
    MethodId getId();

    MethodId getQuickFormattedId(String _genericClass, ContextEl _context);

    boolean isStaticMethod();

    boolean isFinalMethod();

    boolean isAbstractMethod();

}
