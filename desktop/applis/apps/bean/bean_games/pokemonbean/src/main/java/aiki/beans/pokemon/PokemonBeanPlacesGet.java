package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanPlacesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPlInd(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPlaces());
    }
}
