package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilitiesBeanSortedAbilitiesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).sortedAbilitiesGet());
    }
}
