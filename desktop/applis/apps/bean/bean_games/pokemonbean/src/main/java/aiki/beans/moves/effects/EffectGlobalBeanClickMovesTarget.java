package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EffectGlobalBeanClickMovesTarget implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).clickMovesTarget(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
