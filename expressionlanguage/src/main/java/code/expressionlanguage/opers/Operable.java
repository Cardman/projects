package code.expressionlanguage.opers;

import code.expressionlanguage.opers.util.ConstructorId;

public interface Operable {

    boolean isPossibleInitClass();

    boolean isSuperConstructorCall();

    boolean isOtherConstructorClass();

    ConstructorId getConstId();
}
