package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanIgnFoeStatisBoostGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getIgnFoeStatisBoost());
    }
}
