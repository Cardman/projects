package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanClickRequiredStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickRequiredStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
