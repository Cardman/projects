package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectInvokeBeanInvokingTargetChosenMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectInvokeBean) ((PokemonBeanStruct)_instance).getInstance()).getInvokingTargetChosenMove());
    }
}
