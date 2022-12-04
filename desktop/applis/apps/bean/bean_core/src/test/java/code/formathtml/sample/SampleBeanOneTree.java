package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.util.StringMap;

public class SampleBeanOneTree implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        StringMap<Integer> arr_ = new StringMap<Integer>();
        arr_.addEntry("ONE",1);
        arr_.addEntry("TWO",2);
        return BeanNatCommonLgNames.getStrInteger(arr_);
    }
}
