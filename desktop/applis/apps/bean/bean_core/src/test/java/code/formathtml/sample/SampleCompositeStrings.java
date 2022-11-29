package code.formathtml.sample;

import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.util.StringList;

public class SampleCompositeStrings implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        StringList arr_ = new StringList();
        arr_.add("FIRST");
        arr_.add("SECOND");
        return BeanNatCommonLgNames.getStringArray(arr_);
    }
}
