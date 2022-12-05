package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class DifficultyBeanComSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setOwner(((DifficultyCommonStruct)_args[0]).getDifficultyCommon());
        return NaNu.NULL_VALUE;
    }
}
