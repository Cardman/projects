package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;



public final class ExecCatOperation {

    private ExecCatOperation(){}

    public static Struct localSumDiff(Struct _a, Struct _b,
                                        ContextEl _cont) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a,_cont));
        str_.append(getString(_b,_cont));
        return new StringStruct(str_.toString());
    }
    public static String getString(Struct _value,ContextEl _cont) {
        return getDisplayable(_value,_cont).getInstance();
    }
    public static StringStruct getDisplayable(Struct _value,ContextEl _cont) {
        Struct a_ = ArgumentListCall.getNull(_value);
        if (a_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)a_).getDisplayedString(_cont);
        }
        return _cont.getStandards().getStringOfObject(_cont,a_);
    }

    public static StringStruct getStringOfObjectBase(ContextEl _cont, Struct _arg) {
        String str_;
        if (_arg instanceof EnumerableStruct) {
            str_ = ((EnumerableStruct)_arg).getName();
        } else {
            str_ = "";
        }
        if (str_.isEmpty()) {
            return new StringStruct(ArgumentListCall.getNull(_arg).getClassName(_cont));
        }
        return new StringStruct(str_);
    }

}
