package code.expressionlanguage.opers;

import code.expressionlanguage.opers.util.FctConstraints;

public interface Operable {

    boolean isPossibleInitClass();

    boolean isSuperConstructorCall();

    boolean isOtherConstructorClass();

    FctConstraints getConstId();
}
