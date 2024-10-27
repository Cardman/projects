package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.DoubleStruct;
import code.maths.montecarlo.*;
import code.util.CustList;

public final class FctMathNativeRandom0 extends FctMath {

    private final String id;

    public FctMathNativeRandom0(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<String> rds_ = new CustList<String>();
        double rand_ = MonteCarloUtil.pick(_cont.getStandards().getGenerator(),_stackCall.getSeedCust(),_cont.getExecutionInfos().getDbConverter(), rds_);
        FctMathEval.log(_stackCall, rds_, id);
        return new ArgumentWrapper(new DoubleStruct(rand_));
    }
}
