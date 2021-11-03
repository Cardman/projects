package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class FctRangeUpper implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return rgUpper(_instance);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(rgUpper(_instance));
    }

    private static Struct rgUpper(Struct _instance) {
        RangeStruct rangeStruct_ = NumParsers.convertToRange(_instance);
        return new IntStruct(rangeStruct_.getUpper());
    }
}
