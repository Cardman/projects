package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SellerBeanClickTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).clickTm(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
