package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.WithDifficultyCommon;
import code.bean.nat.*;
import code.bean.nat.*;

public class DifficultyBeanComGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new DifficultyCommonStruct(((WithDifficultyCommon)((PokemonBeanStruct)_instance).getInstance()).getDifficultyCommon());
    }
}
