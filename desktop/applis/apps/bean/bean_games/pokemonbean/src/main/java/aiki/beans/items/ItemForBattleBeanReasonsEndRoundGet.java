package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;

public class ItemForBattleBeanReasonsEndRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(((ItemForBattleBean) ((PokemonBeanStruct) _instance).getInstance()).getEndRoundCommon().getReasonsEndRound());
    }
}
