package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanBoostStatisTypesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getBigNatMapSta(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getBoostStatisTypes());
    }
}
