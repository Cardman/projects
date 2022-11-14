package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class MapLevelBeanOutsideGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getOutside());
    }
}
