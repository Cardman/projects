package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class MapLevelBeanLevelIndexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getLevelIndex());
    }
}
