package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BerryBeanGetTrCategRecoil implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getTrCategRecoil(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
