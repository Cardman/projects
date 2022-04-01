package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanMultDrainedHpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDrainedHp(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
