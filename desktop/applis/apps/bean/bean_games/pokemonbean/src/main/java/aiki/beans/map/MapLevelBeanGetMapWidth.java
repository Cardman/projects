package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class MapLevelBeanGetMapWidth implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getMapWidth());
    }
}
