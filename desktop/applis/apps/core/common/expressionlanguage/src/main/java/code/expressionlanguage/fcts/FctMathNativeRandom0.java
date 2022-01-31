package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.DoubleStruct;
import code.maths.montecarlo.AbstractGenerator;

public final class FctMathNativeRandom0 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGenerator generator_ = _cont.getStandards().getGenerator();
        return new ArgumentWrapper(new DoubleStruct(_stackCall.getSeedCust().pick(generator_)));
    }
}
