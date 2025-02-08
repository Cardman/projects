package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanMultStatIfStatutRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWcByteMap(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatIfStatutRank());
    }
}
