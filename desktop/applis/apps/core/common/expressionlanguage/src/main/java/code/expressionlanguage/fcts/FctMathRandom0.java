package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.AbstractFormatParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;

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
            LambdaStruct lda_ = AbstractFormatParamChecker.matchAbstract(seedSpec_, meth_);
            if (lda_ != null) {
                Argument fct_ = new Argument(lda_);
                ExecInvokingOperation.prepareCallDynReflect(fct_,new ArrayStruct(0,""),0,_cont, _stackCall);
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            ExecTemplates.wrapAndCall(p_, cl_,argSeedSpec_, _cont, _stackCall, new ArgumentListCall());
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
            LambdaStruct lda_ = AbstractFormatParamChecker.matchAbstract(seed_, meth_);
            if (lda_ != null) {
                Argument fct_ = new Argument(lda_);
                ExecInvokingOperation.prepareCallDynReflect(fct_,new ArrayStruct(0,""),0,_cont, _stackCall);
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            ExecTemplates.wrapAndCall(p_, cl_,argSeed_, _cont, _stackCall, new ArgumentListCall());
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        return new ArgumentWrapper(new DoubleStruct(_stackCall.getSeedCust().pick(generator_)));
    }
}
