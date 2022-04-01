package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class FightBeanWinningMoneyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).getWinningMoney(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
