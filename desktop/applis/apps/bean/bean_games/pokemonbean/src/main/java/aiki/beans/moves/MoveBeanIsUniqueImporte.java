package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class MoveBeanIsUniqueImporte implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).isUniqueImporte());
    }
}
