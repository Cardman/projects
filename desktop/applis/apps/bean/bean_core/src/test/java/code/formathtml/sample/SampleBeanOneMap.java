package code.formathtml.sample;

import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.util.StringMap;

public class SampleBeanOneMap implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        StringMap<Integer> arr_ = new StringMap<Integer>();
        arr_.addEntry("ONE",1);
        arr_.addEntry("TWO",2);
        return BeanNatCommonLgNames.getStrInteger(arr_);
    }
}
