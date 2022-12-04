package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolutionBeanBaseSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EvolutionBean) ((PokemonBeanStruct)_instance).getInstance()).setBase(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
