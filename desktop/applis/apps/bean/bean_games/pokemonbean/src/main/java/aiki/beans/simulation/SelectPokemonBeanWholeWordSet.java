package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanWholeWordSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getWholeWord().setSelected(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
