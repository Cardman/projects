package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SellerBeanGetTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).getTm(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
