package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class StatusBeanRateForUsingAMoveIfFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getRateForUsingAMoveIfFoe());
    }
}
