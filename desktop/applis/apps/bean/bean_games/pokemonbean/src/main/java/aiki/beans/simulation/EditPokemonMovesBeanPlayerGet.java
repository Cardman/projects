package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanPlayerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).getPlayer());
    }
}
