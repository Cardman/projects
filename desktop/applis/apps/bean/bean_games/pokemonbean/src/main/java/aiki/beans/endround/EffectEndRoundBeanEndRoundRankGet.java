package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundBeanEndRoundRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (EffectEndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).getEndRoundRank());
    }
}
