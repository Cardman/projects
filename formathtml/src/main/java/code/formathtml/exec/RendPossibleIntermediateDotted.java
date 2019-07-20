package code.formathtml.exec;

import code.expressionlanguage.Argument;

public interface RendPossibleIntermediateDotted {

    Argument getPreviousArgument();

    boolean isIntermediateDottedOperation();

    int getOrder();
}
