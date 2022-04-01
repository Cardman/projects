package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class MoveLineBeanIndexSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (MoveLineBean) ((PokemonBeanStruct)_instance).getInstance()).setIndex(NumParsers.convertToNumber(_args[0]).intStruct());
        return NullStruct.NULL_VALUE;
    }
}
