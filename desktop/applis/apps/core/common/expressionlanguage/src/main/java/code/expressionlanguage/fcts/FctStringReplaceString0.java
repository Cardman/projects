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

public final class FctStringReplaceString0 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return repChars(_instance,  _args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct old_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct new_ = argumentWrappers_.get(1).getValue().getStruct();
        return replace(_instance, old_, new_);
    }

    private static ArgumentWrapper replace(Struct _str, Struct _oldChar, Struct _newChar) {
        return new ArgumentWrapper(repChars(_str, _oldChar, _newChar));
    }

    private static StringStruct repChars(Struct _str, Struct _oldChar, Struct _newChar) {
        char oldChar_ = NumParsers.convertToChar(_oldChar).getChar();
        char newChar_ = NumParsers.convertToChar(_newChar).getChar();
        return new StringStruct(((StringStruct)_str).getInstance().replace(oldChar_, newChar_));
    }
}
