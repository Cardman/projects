package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctDoubleIsInfinite1 implements AnaStdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        NumberStruct instance_ = NumParsers.convertToNumber(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        return new ArgumentWrapper(BooleanStruct.of(Double.isInfinite(instance_.doubleStruct())));
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        NumberStruct instance_ = NumParsers.convertToNumber(_args[0]);
        return BooleanStruct.of(Double.isInfinite(instance_.doubleStruct()));
    }
}
