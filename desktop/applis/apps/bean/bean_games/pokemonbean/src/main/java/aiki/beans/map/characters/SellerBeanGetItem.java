package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SellerBeanGetItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).getItem(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
