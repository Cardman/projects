package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonBeanFrontImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getFrontImage());
    }
}
