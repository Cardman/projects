package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundGlobalBeanHealingEndRoundGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getHealingEndRound());
    }
}
