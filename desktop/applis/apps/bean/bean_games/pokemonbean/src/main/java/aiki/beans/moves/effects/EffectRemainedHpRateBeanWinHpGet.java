package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectRemainedHpRateBeanWinHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectRemainedHpRateBean) ((PokemonBeanStruct)_instance).getInstance()).getWinHp());
    }
}
