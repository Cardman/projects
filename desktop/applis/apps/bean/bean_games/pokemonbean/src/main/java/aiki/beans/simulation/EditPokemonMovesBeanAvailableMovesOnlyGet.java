package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanAvailableMovesOnlyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).getAvailableMovesOnly().isSelected());
    }
}
