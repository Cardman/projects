package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.util.StringMap;

public class SampleBeanOneTree implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        StringMap<Long> arr_ = new StringMap<Long>();
        arr_.addEntry("ONE",1L);
        arr_.addEntry("TWO",2L);
        return BeanNatCommonLgNames.getStrInteger(arr_);
    }
}
