package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class HealingHpBeanHpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (HealingHpBean) ((PokemonBeanStruct)_instance).getInstance()).getHp(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
