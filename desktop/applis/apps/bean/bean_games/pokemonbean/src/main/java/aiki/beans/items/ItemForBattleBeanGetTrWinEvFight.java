package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanGetTrWinEvFight implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getTrWinEvFight(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
