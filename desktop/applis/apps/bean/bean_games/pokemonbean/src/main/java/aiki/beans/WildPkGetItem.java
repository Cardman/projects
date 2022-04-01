package aiki.beans;

import aiki.map.pokemon.WildPk;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class WildPkGetItem implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (WildPk) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
