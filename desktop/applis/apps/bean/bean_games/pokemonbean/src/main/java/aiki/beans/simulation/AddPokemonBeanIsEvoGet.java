package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AddPokemonBeanIsEvoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getIsEvo());
    }
}
