package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanGetTrTeamMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getTrTeamMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
