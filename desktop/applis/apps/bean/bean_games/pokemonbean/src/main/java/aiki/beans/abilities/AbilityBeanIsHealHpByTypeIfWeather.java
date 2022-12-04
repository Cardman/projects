package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanIsHealHpByTypeIfWeather implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).isHealHpByTypeIfWeather(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
