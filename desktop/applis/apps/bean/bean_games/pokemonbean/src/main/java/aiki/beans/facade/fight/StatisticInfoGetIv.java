package aiki.beans.facade.fight;

import aiki.beans.fight.StatisticInfoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoGetIv implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((StatisticInfoStruct) _instance).getInstance()).getIv());
    }
}
