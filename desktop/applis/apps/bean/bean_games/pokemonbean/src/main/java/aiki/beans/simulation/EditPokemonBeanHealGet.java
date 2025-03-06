package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanHealGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHeal().isSelected());
    }
}
