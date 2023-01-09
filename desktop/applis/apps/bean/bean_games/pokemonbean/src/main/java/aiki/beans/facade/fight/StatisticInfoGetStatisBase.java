package aiki.beans.facade.fight;

import aiki.beans.fight.StatisticInfoStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class StatisticInfoGetStatisBase implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((StatisticInfoStruct) _instance).getStatisticInfo()).getStatisBase());
    }
}
