package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemForBattleBeanFailStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStrOnly(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getFailStatus());
    }
}
