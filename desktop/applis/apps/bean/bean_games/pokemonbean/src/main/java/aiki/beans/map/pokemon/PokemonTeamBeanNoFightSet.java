package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonTeamBeanNoFightSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).setNoFight(NaPa.convertToNumber(_args[0]).intStruct());
        return NaNu.NULL_VALUE;
    }
}
