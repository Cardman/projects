package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectPokemonBeanGetMiniImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniImagePk(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
