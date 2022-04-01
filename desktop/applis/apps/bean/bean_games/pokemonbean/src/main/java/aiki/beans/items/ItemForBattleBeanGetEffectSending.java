package aiki.beans.items;

import aiki.beans.EffectWhileSendingWithStatisticStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanGetEffectSending implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new EffectWhileSendingWithStatisticStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectSending(),PokemonStandards.TYPE_EFFECT_WHILE_SENDING);
    }
}
