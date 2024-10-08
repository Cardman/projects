package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonBeanImagesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getWcStr(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getImages());
    }
}
