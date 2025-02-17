package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectSwitchMoveTypesBeanReplacingTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectSwitchMoveTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getReplacingTypes());
    }
}
