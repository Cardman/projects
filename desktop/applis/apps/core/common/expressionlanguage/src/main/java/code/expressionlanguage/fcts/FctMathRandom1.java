package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;

public final class FctMathRandom1 extends FctMath {

    private final String id;

    public FctMathRandom1(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return randomParam(_cont,_firstArgs.getArgumentWrappers().get(0).getValue(),_stackCall, id);
    }

    private static ArgumentWrapper randomParam(ContextEl _cont, Struct _args, StackCall _stackCall, String _id) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seedSpec_ = _stackCall.getSeedSpecGenerator();
        Classes classes_ = _cont.getClasses();
        NumberStruct numberStruct_ = NumParsers.convertToNumber(_args);
        if (matchNotNull(_cont, seedSpec_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seedSpec_,classes_.getSeedGeneratorPair(),new CustList<ArgumentWrapper>(new ArgumentWrapper(_args)));
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct seed_ = _stackCall.getSeed();
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            ExecTemplates.prepare(_cont,_stackCall,seed_,classes_.getSeedGeneratorPair(),new CustList<ArgumentWrapper>(new ArgumentWrapper(_args)));
        }
        if (_cont.callsOrException(_stackCall)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        long b_ = numberStruct_.longStruct();
        CustList<String> rds_ = new CustList<String>();
        LongStruct res_ = new LongStruct(MonteCarloUtil.randomLong(b_, generator_, _stackCall.getSeedCust(), _cont.getExecutionInfos().getDbConverter(), rds_));
        FctMathEval.log(_stackCall, rds_, _id +":"+ b_);
        return new ArgumentWrapper(res_);
    }
}
