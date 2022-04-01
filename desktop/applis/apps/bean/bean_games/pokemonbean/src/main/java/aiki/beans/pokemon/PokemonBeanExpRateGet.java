package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanExpRateGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LongStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getExpRate());
    }
}
