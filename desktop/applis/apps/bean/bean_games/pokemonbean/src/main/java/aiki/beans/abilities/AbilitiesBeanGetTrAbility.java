package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilitiesBeanGetTrAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrSortedAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
