package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class StatusBeanRateForFullHealIfMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getRateForFullHealIfMove());
    }
}
