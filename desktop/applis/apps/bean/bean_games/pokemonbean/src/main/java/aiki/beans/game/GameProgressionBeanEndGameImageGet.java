package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class GameProgressionBeanEndGameImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getEndGameImage());
    }
}
