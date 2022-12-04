package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setAllowedSwitchPlacesEndRound(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
