package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class HealingItemBeanHappinessGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (HealingItemBean) ((PokemonBeanStruct)_instance).getInstance()).getHappiness());
    }
}
