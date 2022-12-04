package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanIsTousAdv implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).isTousAdv());
    }
}
