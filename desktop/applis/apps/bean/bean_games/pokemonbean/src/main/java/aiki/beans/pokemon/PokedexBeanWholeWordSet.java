package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class PokedexBeanWholeWordSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).setWholeWord(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
