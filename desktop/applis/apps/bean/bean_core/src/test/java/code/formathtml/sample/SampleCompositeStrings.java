package code.formathtml.sample;

import code.bean.nat.*;
import code.bean.nat.*;
import code.util.StringList;

public class SampleCompositeStrings implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        StringList arr_ = new StringList();
        arr_.add("FIRST");
        arr_.add("SECOND");
        return BeanNatCommonLgNames.getStringArray(arr_);
    }
}
