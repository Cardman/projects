package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundIndividualBeanRecoilDamageGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).getRecoilDamage(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
