package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class TeamBeanPlayerFightersAgainstFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getByteBytes(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getPlayerFightersAgainstFoe());
    }
}
