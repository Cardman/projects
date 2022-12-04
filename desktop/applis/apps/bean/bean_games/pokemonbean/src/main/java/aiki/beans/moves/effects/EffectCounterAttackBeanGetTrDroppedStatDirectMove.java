package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCounterAttackBeanGetTrDroppedStatDirectMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCounterAttackBean) ((PokemonBeanStruct)_instance).getInstance()).getTrDroppedStatDirectMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
