package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;

public final class FctMathRandom1 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return randomParam(_cont,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_stackCall);
    }

    private static ArgumentWrapper randomParam(ContextEl _cont, Struct _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seedSpec_ = _stackCall.getSeedSpecGenerator();
        Classes classes_ = _cont.getClasses();
        NumberStruct numberStruct_ = NumParsers.convertToNumber(_args);
        if (matchNotNull(_cont, seedSpec_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seedSpec_,classes_.getSeedGeneratorPair().getType(),classes_.getSeedGeneratorPair().getFct(),new CustList<Argument>(new Argument(_args)),numberStruct_.getClassName(_cont));
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct seed_ = _stackCall.getSeed();
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seed_,classes_.getSeedGeneratorPair().getType(),classes_.getSeedGeneratorPair().getFct(),new CustList<Argument>(new Argument(_args)),numberStruct_.getClassName(_cont));
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        return new ArgumentWrapper(new LongStruct(MonteCarloUtil.randomLong(numberStruct_.longStruct(),generator_,_stackCall.getSeedCust())));
    }
}
