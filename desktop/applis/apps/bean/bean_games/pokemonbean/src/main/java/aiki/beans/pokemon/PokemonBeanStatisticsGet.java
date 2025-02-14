package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;

public class PokemonBeanStatisticsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStatistic(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getStatistics());
    }
}
