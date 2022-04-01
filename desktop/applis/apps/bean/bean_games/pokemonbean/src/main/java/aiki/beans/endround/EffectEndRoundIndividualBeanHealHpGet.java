package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundIndividualBeanHealHpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).getHealHp(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
