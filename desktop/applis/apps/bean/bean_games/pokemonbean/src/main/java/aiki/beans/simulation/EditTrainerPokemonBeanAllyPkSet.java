package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditTrainerPokemonBeanAllyPkSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getAllyPk().setSelected(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
