package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokemonBeanGetMiniMapImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniMapImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
