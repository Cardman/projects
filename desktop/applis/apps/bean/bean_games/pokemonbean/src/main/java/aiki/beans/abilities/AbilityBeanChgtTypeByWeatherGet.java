package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanChgtTypeByWeatherGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrKey(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getChgtTypeByWeather());
    }
}
