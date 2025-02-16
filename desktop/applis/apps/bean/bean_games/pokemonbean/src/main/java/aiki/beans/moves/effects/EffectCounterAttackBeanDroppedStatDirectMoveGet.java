package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectCounterAttackBeanDroppedStatDirectMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getDroppedStatDirectMove());
    }
}
