package aiki.beans.facade.game.dto;

import aiki.beans.game.StatisticInfoPkPlayerStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatisticInfoPkPlayerGetName implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((StatisticInfoPkPlayerStruct) _instance).getStatisticInfoPkPlayer()).getName());
    }
}
