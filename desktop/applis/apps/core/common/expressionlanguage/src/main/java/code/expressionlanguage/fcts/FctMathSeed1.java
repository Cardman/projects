package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;

public final class FctMathSeed1 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        _stackCall.setSeed(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
