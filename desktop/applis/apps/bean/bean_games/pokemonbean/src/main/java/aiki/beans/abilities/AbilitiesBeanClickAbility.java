package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilitiesBeanClickAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
