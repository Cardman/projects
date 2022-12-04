package aiki.beans.facade.fight;

import aiki.beans.fight.StatisticInfoStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class StatisticInfoGetStatisBase implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((StatisticInfoStruct) _instance).getInstance()).getStatisBase());
    }
}
