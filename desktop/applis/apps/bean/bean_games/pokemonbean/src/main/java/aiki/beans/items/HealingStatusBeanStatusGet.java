package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class HealingStatusBeanStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (HealingStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getStatus());
    }
}
