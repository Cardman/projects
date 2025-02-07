package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanFailStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrOnly(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getFailStatus());
    }
}
