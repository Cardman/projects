package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditTrainerPokemonBeanAllyPkSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).setAllyPk(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
