package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectGlobalBeanMultDamageTypesMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrRateVal(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDamageTypesMoves());
    }
}
