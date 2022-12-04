package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokedexBeanBooleansGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).getBooleans());
    }
}
