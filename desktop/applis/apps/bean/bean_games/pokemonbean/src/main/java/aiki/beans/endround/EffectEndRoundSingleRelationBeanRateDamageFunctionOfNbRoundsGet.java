package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getLongRate(( (EffectEndRoundSingleRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getRateDamageFunctionOfNbRounds());
    }
}
