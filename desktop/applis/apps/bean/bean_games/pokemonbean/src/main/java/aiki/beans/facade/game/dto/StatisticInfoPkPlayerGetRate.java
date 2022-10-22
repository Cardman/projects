package aiki.beans.facade.game.dto;

import aiki.beans.game.StatisticInfoPkPlayerStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoPkPlayerGetRate implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((StatisticInfoPkPlayerStruct) _instance).getInstance()).getRate());
    }
}
