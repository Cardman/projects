package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanBreakFoeImmuneGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getTypesDuo(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getBreakFoeImmune());
    }
}
