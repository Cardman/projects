package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanMultStatPokemonRankGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getWcByteMap(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatPokemonRank());
    }
}
