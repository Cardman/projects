package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class EffectStatisticBeanGetRate implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectStatisticBean) ((PokemonBeanStruct)_instance).getInstance()).getRate(NumParsers.convertToNumber(_args[0]).intStruct()),BeanNatCommonLgNames.TYPE_RATE);
    }
}
