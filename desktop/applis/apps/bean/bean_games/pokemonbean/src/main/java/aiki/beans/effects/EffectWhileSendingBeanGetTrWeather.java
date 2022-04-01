package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanGetTrWeather implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getTrWeather());
    }
}
