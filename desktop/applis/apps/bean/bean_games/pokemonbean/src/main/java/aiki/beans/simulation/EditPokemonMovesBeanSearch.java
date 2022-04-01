package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EditPokemonMovesBeanSearch implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).search();
        return NullStruct.NULL_VALUE;
    }
}
