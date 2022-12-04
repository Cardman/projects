package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleBeanTwoTypedStringSet2 implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        ((NatArrayStruct)_instance).set(0,_args[0]);
//        ((SampleBeanStruct)_instance).setTypedString2(NumParsers.getString(_args[0]).getInstance());
        return _args[0];
    }
}
