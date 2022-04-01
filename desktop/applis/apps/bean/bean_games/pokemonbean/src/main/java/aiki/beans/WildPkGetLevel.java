package aiki.beans;

import aiki.map.pokemon.WildPk;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class WildPkGetLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (WildPk) ((PkStruct)_instance).getWildPk()).getLevel());
    }
}
