package aiki.beans.facade.game.dto;

import aiki.beans.StatisticInfoPkPlayerStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoPkPlayerGetName implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((StatisticInfoPkPlayerStruct) _instance).getInstance()).getName());
    }
}
