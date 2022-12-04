package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTeamBeanGetName implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getName(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
