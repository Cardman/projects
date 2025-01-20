package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class AbilityBeanIncreasedPrioTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getIncreasedPrioTypes());
    }
}
