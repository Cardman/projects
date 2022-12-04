package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class EfficiencyRateGetEff implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((EfficiencyRateStruct) _instance).getInstance()).getEff());
    }
}
