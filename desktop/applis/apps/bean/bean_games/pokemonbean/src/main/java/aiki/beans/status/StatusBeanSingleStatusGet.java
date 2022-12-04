package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanSingleStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getSingleStatus());
    }
}
