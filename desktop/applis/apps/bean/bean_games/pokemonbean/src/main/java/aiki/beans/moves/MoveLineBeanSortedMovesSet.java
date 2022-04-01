package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class MoveLineBeanSortedMovesSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (MoveLineBean) ((PokemonBeanStruct)_instance).getInstance()).setSortedMoves(_args[0]);
        return NullStruct.NULL_VALUE;
    }
}
