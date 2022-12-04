package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class MapLevelBeanClickArea implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickArea(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
