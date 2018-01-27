package code.expressionlanguage.common;

import code.expressionlanguage.opers.util.ConstructorId;


public interface GeneConstructor extends GeneFunction {

    String getSignature();

    String getDeclaringType();

    ConstructorId getId();
    ConstructorId getGenericId();
}
