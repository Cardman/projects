package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanImmuLowStatIfStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.arrId(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuLowStatIfStatus().size());
    }
}
