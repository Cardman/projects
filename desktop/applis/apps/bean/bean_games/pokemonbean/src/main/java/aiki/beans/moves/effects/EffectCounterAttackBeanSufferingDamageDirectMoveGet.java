package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class EffectCounterAttackBeanSufferingDamageDirectMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getSufferingDamageDirectMove());
    }
}
