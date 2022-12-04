package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanBoostStatisTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getBigNatMapSta(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getBoostStatisTypes());
    }
}
