package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCopyMoveBeanGetTrMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
