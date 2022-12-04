package code.formathtml.sample;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleBeanOneMessage implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return(new NaStSt("Test {0}2"));
    }
}
