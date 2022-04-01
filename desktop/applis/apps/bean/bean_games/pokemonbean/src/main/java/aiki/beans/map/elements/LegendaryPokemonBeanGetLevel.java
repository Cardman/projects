package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class LegendaryPokemonBeanGetLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getLevel());
    }
}
