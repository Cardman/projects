package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanGetBase implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getBase(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
