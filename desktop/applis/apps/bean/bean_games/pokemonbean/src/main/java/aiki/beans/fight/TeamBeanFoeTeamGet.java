package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class TeamBeanFoeTeamGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getFoeTeam());
    }
}
