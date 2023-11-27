package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
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
            ExecTemplates.prepare(_cont,_stackCall,seedSpec_,classes_.getSeedDoubleGeneratorPair(),new CustList<Argument>());
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct seed_ = _stackCall.getSeed();
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedDoubleGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seed_,classes_.getSeedDoubleGeneratorPair(),new CustList<Argument>());
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        DoubleStruct res_ = new DoubleStruct(_stackCall.getSeedCust().pick(generator_));
        CustList<String> rds_ = new CustList<String>();
        rds_.add(res_.getDisplayedString(_cont).getInstance());
        FctMathEval.log(_stackCall, rds_, _id);
        return new ArgumentWrapper(res_);
    }
}
