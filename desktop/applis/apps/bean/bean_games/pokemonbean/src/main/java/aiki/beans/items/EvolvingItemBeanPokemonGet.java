package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolvingItemBeanPokemonGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (EvolvingItemBean) ((PokemonBeanStruct)_instance).getInstance()).getPokemon());
    }
}
