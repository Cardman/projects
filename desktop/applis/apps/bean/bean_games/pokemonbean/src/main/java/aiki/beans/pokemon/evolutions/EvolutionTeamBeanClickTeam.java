package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvolutionTeamBeanClickTeam implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EvolutionTeamBean) ((PokemonBeanStruct)_instance).getInstance()).clickTeam(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
