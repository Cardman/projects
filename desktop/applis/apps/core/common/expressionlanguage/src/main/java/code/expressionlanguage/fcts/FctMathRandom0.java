package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;

public final class FctMathRandom0 extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return random(_cont,_stackCall);
    }
    private static ArgumentWrapper random(ContextEl _cont, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seedSpec_ = _stackCall.getSeedSpecDoubleGenerator();
        Argument argSeedSpec_ = new Argument(seedSpec_);
        ExecTypeFunction p_ = new ExecTypeFunction((ExecRootBlock)null,null);
        CustList<Argument> argsToPass_ = new CustList<Argument>();
        ExecFormattedRootBlock cl_ = ExecFormattedRootBlock.defValue();
        if (matchNotNull(_cont, seedSpec_, lgNames_.getContent().getPredefTypes().getAliasSeedDoubleGenerator())) {
            String argClassName_ = seedSpec_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seedSpec_,classes_.getSeedDoubleGeneratorPair());
            p_ = polymorphMeth_.getPair();
            cl_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMeth_.getClassName(), _cont);
        }
        if (p_.getFct() instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)p_.getFct();
            if (seedSpec_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance)seedSpec_).getNamed() == meth_) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seedSpec_).getFunctional());
                return new ArgumentWrapper(ExecInvokingOperation.prepareCallDynReflect(fct_,argsToPass_,_cont, _stackCall).getStruct());
            }
            _stackCall.setCallingState(new CustomFoundMethod(argSeedSpec_,cl_, p_, new Parameters()));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct seed_ = _stackCall.getSeed();
        Argument argSeed_ = new Argument(seed_);
        if (matchNotNull(_cont, seed_, lgNames_.getContent().getPredefTypes().getAliasSeedDoubleGenerator())) {
            String argClassName_ = seed_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seed_,classes_.getSeedDoubleGeneratorPair());
            p_ = polymorphMeth_.getPair();
            cl_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMeth_.getClassName(), _cont);
        }
        if (p_.getFct() instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)p_.getFct();
            if (seed_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance)seed_).getNamed() == meth_) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seed_).getFunctional());
                return new ArgumentWrapper(ExecInvokingOperation.prepareCallDynReflect(fct_,argsToPass_,_cont, _stackCall).getStruct());
            }
            _stackCall.setCallingState(new CustomFoundMethod(argSeed_,cl_, p_, new Parameters()));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        return new ArgumentWrapper(new DoubleStruct(_stackCall.getSeedCust().pick(generator_)));
    }
}
