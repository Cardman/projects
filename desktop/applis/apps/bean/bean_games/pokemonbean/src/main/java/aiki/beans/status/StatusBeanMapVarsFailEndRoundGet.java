package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanMapVarsFailEndRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsFailEndRound());
    }
}
