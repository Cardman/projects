package code.expressionlanguage.common;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;


public interface GeneMethod extends GeneFunction {

    MethodModifier getModifier();
    @Override
    MethodId getId();
    String getDeclaringType();

    MethodId getFormattedId(String _genericClass, ContextEl _context);

    MethodId getFormattedId(ContextEl _context);


    boolean isConcreteInstanceDerivableMethod();

    boolean isConcreteMethod();

    boolean isStaticMethod();

    boolean isFinalMethod();

    boolean isAbstractMethod();

    boolean isNormalMethod();
}
