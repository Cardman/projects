package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectSwitchTypesBeanAddedTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectSwitchTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getAddedTypes());
    }
}
