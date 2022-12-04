package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanClickMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
