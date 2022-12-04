package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolutionBeanClickEvo implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionBean) ((PokemonBeanStruct)_instance).getInstance()).clickEvo(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
