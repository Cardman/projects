package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveBeanMovesLevelLearntByPokemonGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getShStrList(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesLevelLearntByPokemon());
    }
}
