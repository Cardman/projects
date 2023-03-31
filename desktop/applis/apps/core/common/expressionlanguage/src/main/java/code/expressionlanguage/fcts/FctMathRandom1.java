package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
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
        Argument argSeedSpec_ = new Argument(seedSpec_);
        ExecTypeFunction p_ = new ExecTypeFunction((ExecRootBlock)null,null);
        CustList<Argument> argsToPass_ = new CustList<Argument>();
        ExecFormattedRootBlock cl_ = new ExecFormattedRootBlock((ExecRootBlock) null,"");
        NumberStruct numberStruct_ = NumParsers.convertToNumber(_args);
        if (matchNotNull(_cont, seedSpec_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            String argClassName_ = seedSpec_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seedSpec_, classes_.getSeedGeneratorPair());
            p_ = polymorphMeth_.getPair();
            cl_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMeth_.getClassName(), _cont);
            argsToPass_.add(new Argument(_args));
        }
        if (p_.getFct() instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)p_.getFct();
            if (seedSpec_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance)seedSpec_).getNamed() == meth_) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seedSpec_).getFunctional());
                return new ArgumentWrapper(ExecInvokingOperation.prepareCallDynReflect(fct_,ArrayStruct.instance(StringExpUtil.getPrettyArrayType(numberStruct_.getClassName(_cont)),argsToPass_),0,_cont, _stackCall).getStruct());
            }
            ArgumentListCall argList_ = ArgumentListCall.wrapCall(argsToPass_);
            ExecTemplates.wrapAndCall(p_, cl_,argSeedSpec_, _cont, _stackCall, argList_);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        argsToPass_.clear();
        Struct seed_ = _stackCall.getSeed();
        Argument argSeed_ = new Argument(seed_);
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedGenerator())) {
            String argClassName_ = seed_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seed_, classes_.getSeedGeneratorPair());
            p_ = polymorphMeth_.getPair();
            cl_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMeth_.getClassName(), _cont);
            argsToPass_.add(new Argument(_args));
        }
        if (p_.getFct() instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)p_.getFct();
            if (seed_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance)seed_).getNamed() == meth_) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seed_).getFunctional());
                return new ArgumentWrapper(ExecInvokingOperation.prepareCallDynReflect(fct_,ArrayStruct.instance(StringExpUtil.getPrettyArrayType(numberStruct_.getClassName(_cont)),argsToPass_),0,_cont, _stackCall).getStruct());
            }
            ArgumentListCall argList_ = ArgumentListCall.wrapCall(argsToPass_);
            ExecTemplates.wrapAndCall(p_, cl_,argSeed_, _cont, _stackCall, argList_);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        return new ArgumentWrapper(new LongStruct(MonteCarloUtil.randomLong(numberStruct_.longStruct(),generator_,_stackCall.getSeedCust())));
    }
}
