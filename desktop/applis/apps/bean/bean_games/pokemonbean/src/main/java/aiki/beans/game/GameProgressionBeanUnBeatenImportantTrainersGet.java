package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanUnBeatenImportantTrainersGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansGameStd.getTrPlNa(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getUnBeatenImportantTrainers());
    }
}
