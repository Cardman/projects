package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanGetTrBreakFoeImmuneValue implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTrBreakFoeImmuneValue(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
