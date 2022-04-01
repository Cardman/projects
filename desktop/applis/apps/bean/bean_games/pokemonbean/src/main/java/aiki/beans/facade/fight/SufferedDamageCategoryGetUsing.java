package aiki.beans.facade.fight;

import aiki.beans.SufferedDamageCategoryStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class SufferedDamageCategoryGetUsing implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((SufferedDamageCategoryStruct) _instance).getInstance()).getUsing(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
