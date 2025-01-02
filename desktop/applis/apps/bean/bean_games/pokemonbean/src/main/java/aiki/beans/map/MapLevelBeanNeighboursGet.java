package aiki.beans.map;

import aiki.beans.*;
import code.bean.nat.*;

public class MapLevelBeanNeighboursGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getIntStr(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getNeighbours());
    }
}
