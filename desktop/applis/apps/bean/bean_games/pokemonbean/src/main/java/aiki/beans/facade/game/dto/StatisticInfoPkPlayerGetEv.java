package aiki.beans.facade.game.dto;

import aiki.beans.StatisticInfoPkPlayerStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoPkPlayerGetEv implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((StatisticInfoPkPlayerStruct) _instance).getInstance()).getEv());
    }
}
