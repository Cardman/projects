package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundMoveBeanEndRoundRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (EffectEndRoundMoveBean) ((PokemonBeanStruct)_instance).getInstance()).getEndRoundRank());
    }
}
