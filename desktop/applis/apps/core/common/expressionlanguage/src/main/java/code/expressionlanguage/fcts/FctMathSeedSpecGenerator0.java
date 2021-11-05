package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;

public final class FctMathSeedSpecGenerator0 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(_stackCall.getSeedSpecGenerator());
    }
}
