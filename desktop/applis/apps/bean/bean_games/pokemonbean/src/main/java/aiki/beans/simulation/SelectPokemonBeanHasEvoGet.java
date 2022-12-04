package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanHasEvoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHasEvo());
    }
}
