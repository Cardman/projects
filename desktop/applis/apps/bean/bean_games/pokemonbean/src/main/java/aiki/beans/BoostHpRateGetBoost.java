package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BoostHpRateGetBoost implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((BoostHpRateStruct) _instance).getBoostHpRate()).getBoost());
    }
}
