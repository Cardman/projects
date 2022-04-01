package aiki.beans.map;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MapLevelBeanDirsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrBool(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getDirs());
    }
}
