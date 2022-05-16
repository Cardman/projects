package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;



public final class ExecCatOperation {

    private ExecCatOperation(){}

    public static Argument localSumDiff(Argument _a, Argument _b,
                                        ContextEl _cont) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a,_cont));
        str_.append(getString(_b,_cont));
        return new Argument(new StringStruct(str_.toString()));
    }
    public static String getString(Argument _value,ContextEl _cont) {
        return getDisplayable(_value,_cont).getInstance();
    }
    public static StringStruct getDisplayable(Argument _value,ContextEl _cont) {
        Struct a_ = Argument.getNullableValue(_value).getStruct();
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
            str_ =  Argument.getNull(_arg).getClassName(_cont);
        }
        return new StringStruct(str_);
    }

}
