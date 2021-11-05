package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LongStruct;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;

public final class FctMathNativeRandom1 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGenerator generator_ = _cont.getStandards().getGenerator();
        return new ArgumentWrapper(new LongStruct(MonteCarloUtil.randomLong(NumParsers.convertToNumber(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).longStruct(),generator_)));
    }
}
