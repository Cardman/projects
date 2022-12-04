package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanWholeWordSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).setWholeWord(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
