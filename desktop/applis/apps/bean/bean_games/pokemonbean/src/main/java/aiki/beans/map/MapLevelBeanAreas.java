package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class MapLevelBeanAreas implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.arrId(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getWildPokemonAreas().size());
    }
}
