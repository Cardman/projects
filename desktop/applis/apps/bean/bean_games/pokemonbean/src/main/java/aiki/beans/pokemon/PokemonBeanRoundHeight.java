package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanRoundHeight implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).roundHeight());
    }
}
