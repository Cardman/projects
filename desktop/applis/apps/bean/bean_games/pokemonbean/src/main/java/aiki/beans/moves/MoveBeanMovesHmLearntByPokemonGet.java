package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanMovesHmLearntByPokemonGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesHmLearntByPokemon());
    }
}
