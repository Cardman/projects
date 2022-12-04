package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;

public class MapLevelBeanNeighboursGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getShortStr(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getNeighbours());
    }
}
