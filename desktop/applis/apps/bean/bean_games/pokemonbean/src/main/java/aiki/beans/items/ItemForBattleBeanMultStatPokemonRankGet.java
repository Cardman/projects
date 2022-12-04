package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanMultStatPokemonRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWcByteMap(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatPokemonRank());
    }
}
