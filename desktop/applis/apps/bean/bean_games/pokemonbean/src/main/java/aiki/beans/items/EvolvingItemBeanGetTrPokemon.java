package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolvingItemBeanGetTrPokemon implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolvingItemBean) ((PokemonBeanStruct)_instance).getInstance()).getTrPokemon(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
