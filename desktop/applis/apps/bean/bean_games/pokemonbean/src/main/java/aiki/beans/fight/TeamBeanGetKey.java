package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class TeamBeanGetKey implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getKey(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
