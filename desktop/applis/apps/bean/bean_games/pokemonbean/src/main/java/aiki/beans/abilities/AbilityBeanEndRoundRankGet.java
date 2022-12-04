package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanEndRoundRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((AbilityBean) ((PokemonBeanStruct) _instance).getInstance()).getEndRoundCommon().getEndRoundRank());
    }
}
