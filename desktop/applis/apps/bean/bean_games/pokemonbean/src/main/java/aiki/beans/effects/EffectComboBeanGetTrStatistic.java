package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectComboBeanGetTrStatistic implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getTrStatistic(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
