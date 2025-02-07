package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public class MoveBeanAffectedByMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getAffectedByMoves());
    }
}
