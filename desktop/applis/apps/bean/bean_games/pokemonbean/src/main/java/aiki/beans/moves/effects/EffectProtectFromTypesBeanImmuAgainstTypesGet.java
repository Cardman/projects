package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectProtectFromTypesBeanImmuAgainstTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectProtectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuAgainstTypes());
    }
}
