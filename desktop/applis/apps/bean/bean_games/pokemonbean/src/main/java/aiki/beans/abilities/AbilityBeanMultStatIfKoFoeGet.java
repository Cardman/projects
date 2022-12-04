package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanMultStatIfKoFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStaByte(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatIfKoFoe());
    }
}
