package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleBeanTwoTypedString2 implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return ((NatArrayStruct)_instance).get(0);
    }
}
