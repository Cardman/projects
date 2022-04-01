package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanClickTechnicalMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickTechnicalMove(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
