package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonBeanEggGroupsPkGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getEggGroupsPk());
    }
}
