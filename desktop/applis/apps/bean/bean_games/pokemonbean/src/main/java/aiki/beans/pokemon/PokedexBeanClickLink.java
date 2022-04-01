package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PokedexBeanClickLink implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
