package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanWholeWordGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getWholeWord());
    }
}
