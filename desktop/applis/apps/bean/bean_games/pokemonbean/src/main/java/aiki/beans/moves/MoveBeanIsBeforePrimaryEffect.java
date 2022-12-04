package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanIsBeforePrimaryEffect implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).isBeforePrimaryEffect(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
