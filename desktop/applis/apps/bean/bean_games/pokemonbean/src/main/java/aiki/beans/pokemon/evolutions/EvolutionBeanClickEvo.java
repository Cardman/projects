package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EvolutionBeanClickEvo implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionBean) ((PokemonBeanStruct)_instance).getInstance()).clickEvo());
    }
}
