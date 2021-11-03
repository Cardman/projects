package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctStringCompare implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return compareToString(_args[0],_args[1]);
    }

    private static Struct compareToString(Struct _str, Struct _anotherString) {
        if (!(_str instanceof StringStruct)) {
            return null;
        }
        StringStruct first_ = (StringStruct) _str;
        if (!(_anotherString instanceof StringStruct)) {
            return null;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        return new IntStruct(StringUtil.compareStrings(first_.getInstance(),st_.getInstance()));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return compareToString(one_,two_,_cont,_stackCall);
    }

    private static ArgumentWrapper compareToString(Struct _str, Struct _anotherString, ContextEl _context, StackCall _stackCall) {
        if (!(_str instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StringStruct first_ = (StringStruct) _str;
        if (!(_anotherString instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StringStruct st_ = (StringStruct)_anotherString;
        return new ArgumentWrapper(new IntStruct(StringUtil.compareStrings(first_.getInstance(),st_.getInstance())));
    }
}
