package aiki.beans.map;

import aiki.beans.*;
import code.bean.nat.*;

public class MapLevelBeanWhiteTilesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPtStr(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getWhiteTiles());
    }
}
