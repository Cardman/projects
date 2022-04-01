package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class MapBeanClickLevelZero implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (MapBean) ((PokemonBeanStruct)_instance).getInstance()).clickLevel(NumParsers.convertToNumber(_args[0]).intStruct(),0));
    }
}
