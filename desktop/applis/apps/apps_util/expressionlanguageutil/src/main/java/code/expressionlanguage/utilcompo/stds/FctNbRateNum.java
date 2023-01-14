package code.expressionlanguage.utilcompo.stds;


import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;

public final class FctNbRateNum implements AnaStdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new RateStruct(new Rate(((RateStruct)_instance).getRate().getNumerator())));
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return new RateStruct(new Rate(RateStruct.def(_instance).getRate().getNumerator()));
    }
}
