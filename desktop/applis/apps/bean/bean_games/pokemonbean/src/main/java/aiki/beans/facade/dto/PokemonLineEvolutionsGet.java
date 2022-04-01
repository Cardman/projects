package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonLineEvolutionsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((PkLineStruct) _instance).getWildPk()).getEvolutions());
    }
}
