package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanGetTrTypesPkAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getTrTypesPkAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
