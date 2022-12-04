package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTeamBeanTeamGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getTeam());
    }
}
