package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectStatusBeanGetTrLink implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getTrLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
