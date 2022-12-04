package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectComboBeanRankIncrementNbRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getRankIncrementNbRound());
    }
}
