package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class GameProgressionBeanHeroImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getHeroImage());
    }
}
