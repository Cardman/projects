package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class PokedexBeanTypedTypeSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedType(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
