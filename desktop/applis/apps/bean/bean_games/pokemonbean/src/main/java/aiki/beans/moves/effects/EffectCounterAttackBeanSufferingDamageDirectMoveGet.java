package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectCounterAttackBeanSufferingDamageDirectMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getSufferingDamageDirectMove());
    }
}
