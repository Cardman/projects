package aiki.beans.map.characters;

import aiki.beans.*;
import code.bean.nat.*;
public class SellerBeanGetAllTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).getAllTm());
    }
}
