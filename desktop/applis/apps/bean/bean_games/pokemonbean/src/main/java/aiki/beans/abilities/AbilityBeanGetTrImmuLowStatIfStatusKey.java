package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanGetTrImmuLowStatIfStatusKey implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTrImmuLowStatIfStatusKey(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
