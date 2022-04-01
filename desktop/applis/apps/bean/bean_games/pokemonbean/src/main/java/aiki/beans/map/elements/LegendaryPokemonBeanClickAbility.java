package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class LegendaryPokemonBeanClickAbility implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbility());
    }
}
