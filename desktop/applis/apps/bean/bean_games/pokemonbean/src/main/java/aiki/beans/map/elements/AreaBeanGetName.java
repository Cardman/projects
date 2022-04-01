package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AreaBeanGetName implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getName(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
