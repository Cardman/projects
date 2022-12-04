package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditTrainerPokemonBeanAllyPkGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getAllyPk());
    }
}
