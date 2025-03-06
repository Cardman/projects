package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanHealSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHeal().setSelected(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
