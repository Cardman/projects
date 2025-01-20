package aiki.beans.game;

import code.bean.nat.*;
public class PokemonPlayerBeanGetEvo implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((ImgPkPlayerStruct)_instance).translation());
    }
}
