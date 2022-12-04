package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class TeamBeanEnabledMovesByGroupGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getStrAct(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getEnabledMovesByGroup());
    }
}
