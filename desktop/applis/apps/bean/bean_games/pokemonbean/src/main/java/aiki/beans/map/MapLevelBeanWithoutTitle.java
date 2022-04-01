package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class MapLevelBeanWithoutTitle implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).withoutTitle(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
