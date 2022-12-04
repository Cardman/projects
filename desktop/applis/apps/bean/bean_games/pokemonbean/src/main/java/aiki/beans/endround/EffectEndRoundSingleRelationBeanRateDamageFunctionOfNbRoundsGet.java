package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getLongRate(( (EffectEndRoundSingleRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getRateDamageFunctionOfNbRounds());
    }
}
