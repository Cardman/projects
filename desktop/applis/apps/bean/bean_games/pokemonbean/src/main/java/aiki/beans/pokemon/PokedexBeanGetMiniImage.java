package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public class PokedexBeanGetMiniImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniImagePk(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
