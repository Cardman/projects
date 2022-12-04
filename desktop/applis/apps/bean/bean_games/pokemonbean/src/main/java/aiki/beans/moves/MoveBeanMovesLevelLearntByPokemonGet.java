package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanMovesLevelLearntByPokemonGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getShStrList(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesLevelLearntByPokemon());
    }
}
