package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTeamBeanNoFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getNoFight());
    }
}
