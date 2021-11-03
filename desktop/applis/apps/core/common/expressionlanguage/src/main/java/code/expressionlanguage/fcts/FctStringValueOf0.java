package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringValueOf0 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        Struct arr_ = _args[0];
        if (!(arr_ instanceof ArrayStruct)) {
            return AnaApplyCoreMethodUtil.getAnaDisplayable(arr_).getDisplayedString(_page);
        }
        return new StringStruct(String.valueOf(FctStringValueOf1.toCharArr((ArrayStruct) arr_)));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct arr_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(arr_ instanceof ArrayStruct)) {
            return new ArgumentWrapper(ExecCatOperation.getDisplayable(new Argument(arr_),_cont));
        }
        return new ArgumentWrapper(new StringStruct(String.valueOf(FctStringValueOf1.toCharArr((ArrayStruct) arr_))));
    }
}
