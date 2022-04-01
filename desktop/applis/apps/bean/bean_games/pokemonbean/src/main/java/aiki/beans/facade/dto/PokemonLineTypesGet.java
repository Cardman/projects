package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonLineTypesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( ((PkLineStruct) _instance).getWildPk()).getTypes());
    }
}
