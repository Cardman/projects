package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectAbilityBeanGetTrAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectAbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTrSortedAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
