package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanMultEvtRateSecEffGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getMultEvtRateSecEff(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
