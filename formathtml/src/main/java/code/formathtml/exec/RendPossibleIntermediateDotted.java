package code.formathtml.exec;

import code.expressionlanguage.Argument;

public interface RendPossibleIntermediateDotted {

    Argument getPreviousArgument();
    void setPreviousArgument(Argument _argument);
    boolean isIntermediateDottedOperation();
}
