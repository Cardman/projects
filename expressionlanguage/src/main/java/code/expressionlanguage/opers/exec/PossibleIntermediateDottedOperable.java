package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;

public interface PossibleIntermediateDottedOperable extends Operable{
    Argument getPreviousArgument();
    void setPreviousArgument(Argument _argument);
}
