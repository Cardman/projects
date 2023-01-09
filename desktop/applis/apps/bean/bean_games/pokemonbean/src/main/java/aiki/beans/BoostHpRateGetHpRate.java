package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class BoostHpRateGetHpRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((BoostHpRateStruct) _instance).getBoostHpRate()).getHpRate());
    }
}
