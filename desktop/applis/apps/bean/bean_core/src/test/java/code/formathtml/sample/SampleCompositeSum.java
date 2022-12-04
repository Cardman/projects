package code.formathtml.sample;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleCompositeSum implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return new NaNbSt(NaPa.convertToNumber(_args[0]).longStruct()+NaPa.convertToNumber(_args[1]).longStruct());
    }

}
