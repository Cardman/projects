package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectWhileSendingBeanEnabledWeatherGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getEnabledWeather().getKey());
    }
}
