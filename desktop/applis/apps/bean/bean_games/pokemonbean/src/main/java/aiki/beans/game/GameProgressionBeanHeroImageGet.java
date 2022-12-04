package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanHeroImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getHeroImage());
    }
}
