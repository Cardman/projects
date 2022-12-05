package aiki.beans.facade.game.dto;

import aiki.beans.game.StatisticInfoPkPlayerStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatisticInfoPkPlayerGetIv implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((StatisticInfoPkPlayerStruct) _instance).getStatisticInfoPkPlayer()).getIv());
    }
}
