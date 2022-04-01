package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveLineBeanMoveLineGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.newMoveLine(( (MoveLineBean) ((PokemonBeanStruct)_instance).getInstance()).getMoveLine());
    }
}
