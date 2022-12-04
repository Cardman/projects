package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class ItemForBattleBeanMultTrappingDamageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getMultTrappingDamage());
    }
}
