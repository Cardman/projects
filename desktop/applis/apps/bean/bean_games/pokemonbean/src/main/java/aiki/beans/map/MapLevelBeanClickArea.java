package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class MapLevelBeanClickArea implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickArea(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
