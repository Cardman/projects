package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectSwitchTypesBeanGetTrConstType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectSwitchTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrConstType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
