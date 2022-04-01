package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class AreaBeanGetMovesAtLevelFishing implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesAtLevelFishing(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
