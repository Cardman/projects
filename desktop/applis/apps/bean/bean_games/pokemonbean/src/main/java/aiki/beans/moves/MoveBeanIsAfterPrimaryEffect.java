package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanIsAfterPrimaryEffect implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).isAfterPrimaryEffect(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
