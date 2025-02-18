package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanImmuLowStatisTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrListStaList(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuLowStatisTypes());
    }
}
