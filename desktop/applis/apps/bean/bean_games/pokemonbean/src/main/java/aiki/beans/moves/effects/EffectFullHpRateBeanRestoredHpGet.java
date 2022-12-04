package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectFullHpRateBeanRestoredHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectFullHpRateBean) ((PokemonBeanStruct)_instance).getInstance()).getRestoredHp());
    }
}
