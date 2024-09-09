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
import code.util.CustList;

public final class FctMathNativeRandom1 extends FctMath {

    private final String id;

    public FctMathNativeRandom1(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGenerator generator_ = _cont.getStandards().getGenerator();
        long b_ = NumParsers.convertToNumber(_firstArgs.getArgumentWrappers().get(0).getValue()).longStruct();
        CustList<String> rds_ = new CustList<String>();
        LongStruct res_ = new LongStruct(MonteCarloUtil.randomLong(b_, generator_, _stackCall.getSeedCust(),rds_));
        FctMathEval.log(_stackCall, rds_, id +":"+ b_);
        return new ArgumentWrapper(res_);
    }
}
