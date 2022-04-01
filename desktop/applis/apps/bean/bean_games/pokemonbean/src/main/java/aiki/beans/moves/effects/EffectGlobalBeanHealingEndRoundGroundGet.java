package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectGlobalBeanHealingEndRoundGroundGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getHealingEndRoundGround(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
