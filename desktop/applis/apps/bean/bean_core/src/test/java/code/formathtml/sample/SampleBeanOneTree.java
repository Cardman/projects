package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public class SampleBeanOneTree implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        StringMap<Integer> arr_ = new StringMap<Integer>();
        arr_.addEntry("ONE",1);
        arr_.addEntry("TWO",2);
        return BeanNatCommonLgNames.getStrInteger(arr_);
    }
}
