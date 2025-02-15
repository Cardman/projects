package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public class MoveBeanIsDamagingDirectMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(CommonBean.toBool(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).isDamagingDirectMove()));
    }
}
