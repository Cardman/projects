package code.expressionlanguage.common;

import code.expressionlanguage.opers.util.ConstructorId;



public interface GeneConstructor extends GeneFunction {

    String getDeclaringType();
    @Override
    ConstructorId getId();
}
