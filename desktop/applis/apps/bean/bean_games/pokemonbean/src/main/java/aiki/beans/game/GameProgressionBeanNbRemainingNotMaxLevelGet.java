package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanNbRemainingNotMaxLevelGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getNbRemainingNotMaxLevel());
    }
}
