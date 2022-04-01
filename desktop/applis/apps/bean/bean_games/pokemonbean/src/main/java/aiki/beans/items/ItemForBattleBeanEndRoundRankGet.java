package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanEndRoundRankGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getEndRoundRank());
    }
}
