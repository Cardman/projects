package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;

public class DifficultyBeanRateWinMoneyBaseGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(((DifficultyBean) ((PokemonBeanStruct) _instance).getInstance()).getDifficultyCommon().getRateWinMoneyBase(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
