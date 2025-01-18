package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class TeamBeanGetMembers implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getIntArray(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getMembers());
    }
}
