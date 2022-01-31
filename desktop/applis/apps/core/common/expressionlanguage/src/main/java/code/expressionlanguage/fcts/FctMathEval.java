package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.litteraladv.MaParser;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.Replacement;

public final class FctMathEval extends FctMath {
    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct st_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct seps_ = argumentWrappers_.get(1).getValue().getStruct();
        return eval(st_, seps_, _cont, _stackCall);
    }


    private static ArgumentWrapper eval(Struct _st, Struct _seps, ContextEl _context, StackCall _stackCall) {
        LgNames lgNames_ = _context.getStandards();
        AbstractGenerator generator_ = lgNames_.getGenerator();
        String val_ = NumParsers.getStringValue(_st);
        CustList<Replacement> repls_ = NumParsers.getReplValue(_seps);
        return new ArgumentWrapper(new StringStruct(MaParser.processEl(generator_,_stackCall.getSeedCust(),val_,repls_)));
    }
}
