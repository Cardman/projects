package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;

public final class FctMathRandom0 extends FctMath {

    private final String id;

    public FctMathRandom0(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return random(_cont,_stackCall, id);
    }
    private static ArgumentWrapper random(ContextEl _cont, StackCall _stackCall, String _id) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seedSpec_ = _stackCall.getSeedSpecDoubleGenerator();
        Classes classes_ = _cont.getClasses();
        if (matchNotNull(_cont, seedSpec_, lgNames_.getContent().getPredefTypes().getAliasSeedDoubleGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seedSpec_,classes_.getSeedDoubleGeneratorPair(),new CustList<ArgumentWrapper>());
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct seed_ = _stackCall.getSeed();
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedDoubleGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seed_,classes_.getSeedDoubleGeneratorPair(),new CustList<ArgumentWrapper>());
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<String> rds_ = new CustList<String>();
        double rand_ = MonteCarloUtil.pick(_cont.getStandards().getGenerator(),_stackCall.getSeedCust(),rds_);
        FctMathEval.log(_stackCall, rds_, _id);
        return new ArgumentWrapper(new DoubleStruct(rand_));
    }
}
