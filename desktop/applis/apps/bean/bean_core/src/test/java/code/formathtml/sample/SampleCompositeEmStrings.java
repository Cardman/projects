package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleCompositeEmStrings implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        NatArrayStruct arr_ = new NatArrayStruct(2);
        arr_.set(0,new NaStSt(""));
        arr_.set(1,new NaStSt("SECOND"));
        return arr_;
    }
}
