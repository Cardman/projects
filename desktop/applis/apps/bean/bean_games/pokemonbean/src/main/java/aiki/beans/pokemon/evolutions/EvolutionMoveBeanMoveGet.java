package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import code.bean.nat.*;
public class EvolutionMoveBeanMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionMoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMove().getTranslation());
    }
}
