package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectAbilityBeanSortedAbilitiesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (SelectAbilityBean) ((PokemonBeanStruct)_instance).getInstance()).sortedAbilitiesGet());
    }
}
