package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanImmuWeatherGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuWeather());
    }
}
