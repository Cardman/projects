package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctNbToDouble implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return convInteger(_instance);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(convInteger(_instance));
    }

    private static Struct convInteger(Struct _from) {
        NumberStruct instance_ = NumParsers.convertToNumber(_from);
        return new DoubleStruct(instance_.doubleStruct());
    }
}
