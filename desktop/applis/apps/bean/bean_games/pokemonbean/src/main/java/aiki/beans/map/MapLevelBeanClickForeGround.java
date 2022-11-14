package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class MapLevelBeanClickForeGround implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickForeGround(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
