package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCounterAttackBeanGetTrSufferingDamageTypes implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getTrSufferingDamageTypes(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
