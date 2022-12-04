package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DealerBeanGetTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)_instance).getInstance()).getTmDealer(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
