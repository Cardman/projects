package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DifficultyCommonBeanRestoredMovesEndFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getRestoredMovesEndFight());
    }
}
