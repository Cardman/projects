package aiki.beans.facade.fight;

import aiki.beans.*;
import aiki.beans.fight.*;
import code.bean.nat.*;
public class StatisticInfoIsBoost implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(CommonBean.toBool(( ((StatisticInfoStruct) _instance).getStatisticInfo()).boost()));
    }
}
