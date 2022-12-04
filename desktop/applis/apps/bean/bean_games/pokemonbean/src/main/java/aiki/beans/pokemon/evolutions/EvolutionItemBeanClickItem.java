package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolutionItemBeanClickItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionItemBean) ((PokemonBeanStruct)_instance).getInstance()).clickItem(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
