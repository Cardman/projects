package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectSwitchMoveTypesBeanGetTrReplacingTypes implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectSwitchMoveTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrReplacingTypes(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
