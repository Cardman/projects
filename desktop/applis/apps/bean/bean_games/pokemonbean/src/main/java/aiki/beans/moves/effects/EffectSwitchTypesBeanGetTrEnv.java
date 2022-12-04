package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectSwitchTypesBeanGetTrEnv implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectSwitchTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrEnv(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
