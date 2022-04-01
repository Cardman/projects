package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanHealHpWhileUsingBerryGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getHealHpWhileUsingBerry(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
