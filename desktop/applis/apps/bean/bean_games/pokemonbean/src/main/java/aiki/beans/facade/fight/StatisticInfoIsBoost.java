package aiki.beans.facade.fight;

import aiki.beans.StatisticInfoStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoIsBoost implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((StatisticInfoStruct) _instance).getInstance()).isBoost());
    }
}
