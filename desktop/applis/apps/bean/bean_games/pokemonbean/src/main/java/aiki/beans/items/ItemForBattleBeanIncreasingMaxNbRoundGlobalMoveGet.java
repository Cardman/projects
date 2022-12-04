package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrShort(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getIncreasingMaxNbRoundGlobalMove());
    }
}
