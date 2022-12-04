package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BoostBeanGetTrHappiness implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).getTrHappiness(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
