package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctStringReplaceString1 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return repStr(_instance, _args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct old_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct new_ = argumentWrappers_.get(1).getValue().getStruct();
        return replaceString(_instance,old_,new_);
    }

    private static ArgumentWrapper replaceString(Struct _str, Struct _oldChar, Struct _newChar) {
        return new ArgumentWrapper(repStr(_str, _oldChar, _newChar));
    }

    private static StringStruct repStr(Struct _str, Struct _oldChar, Struct _newChar) {
        String old_ = NumParsers.getStringValue(_oldChar);
        String new_ = NumParsers.getStringValue(_newChar);
        String out_ = StringUtil.replace(((StringStruct)_str).getInstance(), old_, new_);
        return new StringStruct(out_);
    }
}
