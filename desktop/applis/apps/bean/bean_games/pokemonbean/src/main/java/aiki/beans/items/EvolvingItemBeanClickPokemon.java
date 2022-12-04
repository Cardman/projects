package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolvingItemBeanClickPokemon implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolvingItemBean) ((PokemonBeanStruct)_instance).getInstance()).clickPokemon(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
