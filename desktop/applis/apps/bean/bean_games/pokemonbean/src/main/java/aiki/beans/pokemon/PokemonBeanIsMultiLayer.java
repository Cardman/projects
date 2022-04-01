package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanIsMultiLayer implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).isMultiLayer(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
