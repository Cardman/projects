package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class ItemForBattleBeanWinEvFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStaByte(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getWinEvFight());
    }
}
