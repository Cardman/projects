package code.formathtml.sample;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SampleBeanOneLen implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return(new NaNbSt(NaPa.getString(_args[0]).getInstance().length()));
    }
}
