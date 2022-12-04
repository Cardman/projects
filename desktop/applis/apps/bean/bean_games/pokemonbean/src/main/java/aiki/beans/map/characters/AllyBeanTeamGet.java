package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class AllyBeanTeamGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPkTrainerArray(( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).getTeam());
    }
}
