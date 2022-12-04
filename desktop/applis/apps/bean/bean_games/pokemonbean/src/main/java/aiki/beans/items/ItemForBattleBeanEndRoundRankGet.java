package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanEndRoundRankGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((ItemForBattleBean) ((PokemonBeanStruct) _instance).getInstance()).getEndRoundCommon().getEndRoundRank());
    }
}
