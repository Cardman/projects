package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolutionStoneBeanClickStone implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionStoneBean) ((PokemonBeanStruct)_instance).getInstance()).clickStone(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
