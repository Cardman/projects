package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class GameProgressionBeanGetImagePokemonPartial implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getImagePokemonPartial(NaPa.convertToNumber(_args[0]).intStruct(),NaPa.convertToNumber(_args[1]).intStruct(),NaPa.convertToNumber(_args[2]).intStruct()));
    }
}
