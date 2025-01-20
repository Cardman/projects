package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
public class DifficultyCommonBeanIvPlayerSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setIvPlayer(NaPa.convertToNumber(_args[0]).longStruct());
        return NaNu.NULL_VALUE;
    }
}
