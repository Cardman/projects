package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public class PokedexBeanHasEvoSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).setHasEvo(NumParsers.getString(_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
