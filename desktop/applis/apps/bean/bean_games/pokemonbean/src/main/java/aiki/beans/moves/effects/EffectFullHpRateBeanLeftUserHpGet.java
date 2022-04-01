package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectFullHpRateBeanLeftUserHpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectFullHpRateBean) ((PokemonBeanStruct)_instance).getInstance()).getLeftUserHp(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
