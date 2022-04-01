package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class MoveBeanClickRequiredStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickRequiredStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
