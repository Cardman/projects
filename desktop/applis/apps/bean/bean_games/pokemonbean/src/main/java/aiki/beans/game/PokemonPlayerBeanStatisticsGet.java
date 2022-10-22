package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerBeanStatisticsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansGameStd.getStPkPl(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getStatistics());
    }
}
