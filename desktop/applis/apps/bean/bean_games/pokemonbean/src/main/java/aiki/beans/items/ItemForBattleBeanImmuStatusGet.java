package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemForBattleBeanImmuStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuStatus());
    }
}
