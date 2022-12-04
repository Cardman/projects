package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusSetBeanGetTrStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).getTrStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
