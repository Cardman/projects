package aiki.beans.facade.dto;

import aiki.beans.PkLineStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonLineTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( ((PkLineStruct) _instance).getWildPk()).getTypes());
    }
}
