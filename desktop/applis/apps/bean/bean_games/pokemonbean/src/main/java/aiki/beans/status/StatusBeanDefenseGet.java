package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanDefenseGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getDefense());
    }
}
