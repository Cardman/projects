package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonLineDisplayNameGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((PkLineStruct) _instance).getWildPk()).getDisplayName());
    }
}
