package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanBooleansGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getBooleans());
    }
}
