package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctByteStrHex implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return convert(_args[0], _page.getDisplayedStrings().getAlphaHex());
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(convert(_firstArgs.getArgumentWrappers().get(0).getValue(), _cont.getStandards().getDisplayedStrings().getAlphaHex()));
    }

    public static Struct convert(Struct _arg, String _hex) {
        byte one_ = NumParsers.convertToNumber(_arg).byteStruct();
        return new StringStruct(StringExpUtil.toByteGeneHex(one_, _hex));
    }
}
