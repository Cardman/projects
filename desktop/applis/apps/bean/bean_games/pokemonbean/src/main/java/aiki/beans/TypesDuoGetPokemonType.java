package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class TypesDuoGetPokemonType implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((TypesDuoStruct) _instance).getInstance()).getPokemonType());
    }
}
