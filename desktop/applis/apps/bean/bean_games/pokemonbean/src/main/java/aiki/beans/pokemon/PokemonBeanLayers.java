package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanLayers implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getLayers(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).layers(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
