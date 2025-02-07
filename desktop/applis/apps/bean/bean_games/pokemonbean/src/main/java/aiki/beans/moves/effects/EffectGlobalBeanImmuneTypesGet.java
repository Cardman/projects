package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectGlobalBeanImmuneTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuneTypes());
    }
}
