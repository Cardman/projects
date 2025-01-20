package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;

public class PokemonPlayerBeanGetEvoImg implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(((ImgPkPlayerStruct)_instance).image());
    }
}
