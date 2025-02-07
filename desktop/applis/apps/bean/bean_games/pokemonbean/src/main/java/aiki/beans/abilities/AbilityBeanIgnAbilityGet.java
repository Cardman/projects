package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanIgnAbilityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getIgnAbility());
    }
}
