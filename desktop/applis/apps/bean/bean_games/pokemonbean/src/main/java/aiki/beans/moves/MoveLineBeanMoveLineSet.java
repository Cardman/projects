package aiki.beans.moves;

import aiki.beans.MvLineStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class MoveLineBeanMoveLineSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (MoveLineBean) ((PokemonBeanStruct)_instance).getInstance()).setMoveLine(((MvLineStruct)_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
