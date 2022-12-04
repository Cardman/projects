package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCloneBeanGetTrMovesEndRound implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCloneBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMovesEndRound(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
