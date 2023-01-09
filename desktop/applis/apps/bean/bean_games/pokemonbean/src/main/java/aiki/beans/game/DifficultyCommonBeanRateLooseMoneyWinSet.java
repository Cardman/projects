package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
import code.bean.nat.*;

public class DifficultyCommonBeanRateLooseMoneyWinSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setRateLooseMoneyWin(RtSt.convertToRate(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
