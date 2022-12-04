package aiki.beans.items;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanGetEffectSending implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new EffectWhileSendingWithStatisticStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectSending());
    }
}
