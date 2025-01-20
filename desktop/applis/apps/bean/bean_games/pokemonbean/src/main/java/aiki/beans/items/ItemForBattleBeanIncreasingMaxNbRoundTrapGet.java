package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class ItemForBattleBeanIncreasingMaxNbRoundTrapGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLong(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getIncreasingMaxNbRoundTrap());
    }
}
