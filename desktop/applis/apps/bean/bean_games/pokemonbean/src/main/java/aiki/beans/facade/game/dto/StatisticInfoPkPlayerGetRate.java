package aiki.beans.facade.game.dto;

import aiki.beans.game.StatisticInfoPkPlayerStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class StatisticInfoPkPlayerGetRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((StatisticInfoPkPlayerStruct) _instance).getStatisticInfoPkPlayer()).getRate());
    }
}
