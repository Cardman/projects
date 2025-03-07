package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EvolutionMoveBeanClickMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionMoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickMove());
    }
}
