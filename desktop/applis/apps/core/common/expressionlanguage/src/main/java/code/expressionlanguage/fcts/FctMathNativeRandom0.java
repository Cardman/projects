package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.DoubleStruct;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;

public final class FctMathNativeRandom0 extends FctMath {

    private final String id;

    public FctMathNativeRandom0(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGenerator generator_ = _cont.getStandards().getGenerator();
        DoubleStruct res_ = new DoubleStruct(_stackCall.getSeedCust().pick(generator_));
        CustList<String> rds_ = new CustList<String>();
        rds_.add(res_.getDisplayedString(_cont).getInstance());
        FctMathEval.log(_stackCall, rds_, id);
        return new ArgumentWrapper(res_);
    }
}
