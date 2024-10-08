package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonPlayerBeanImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getImage());
    }
}
