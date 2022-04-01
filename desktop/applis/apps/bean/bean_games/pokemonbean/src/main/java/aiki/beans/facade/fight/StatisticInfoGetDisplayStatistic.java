package aiki.beans.facade.fight;

import aiki.beans.StatisticInfoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoGetDisplayStatistic implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((StatisticInfoStruct) _instance).getInstance()).getDisplayStatistic());
    }
}
