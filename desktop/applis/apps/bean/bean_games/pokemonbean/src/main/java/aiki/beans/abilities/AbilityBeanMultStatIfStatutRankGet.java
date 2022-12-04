package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanMultStatIfStatutRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStatisticStatusByteMap(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatIfStatutRank());
    }
}
