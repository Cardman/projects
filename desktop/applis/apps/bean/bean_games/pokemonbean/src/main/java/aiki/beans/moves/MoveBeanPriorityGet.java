package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanPriorityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getPriority());
    }
}
