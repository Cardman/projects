package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectSwitchMoveTypesBeanGetTrChangedTypes implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectSwitchMoveTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrChangedTypes(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
