package aiki.beans.facade.fight;

import aiki.beans.StatisticInfoStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class StatisticInfoGetStatisBase implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((StatisticInfoStruct) _instance).getInstance()).getStatisBase(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
