package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BoostBeanGetTrEv implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).getTrEv(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
