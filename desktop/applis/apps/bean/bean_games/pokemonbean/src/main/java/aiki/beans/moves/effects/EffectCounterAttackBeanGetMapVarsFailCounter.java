package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCounterAttackBeanGetMapVarsFailCounter implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsFailCounter());
    }
}
