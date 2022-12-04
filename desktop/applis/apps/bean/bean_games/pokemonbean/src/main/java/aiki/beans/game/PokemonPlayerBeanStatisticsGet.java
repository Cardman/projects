package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerBeanStatisticsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansGameStd.getStPkPl(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getStatistics());
    }
}
