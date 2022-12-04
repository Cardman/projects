package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class SellerBeanGetAllTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).getAllTm());
    }
}
