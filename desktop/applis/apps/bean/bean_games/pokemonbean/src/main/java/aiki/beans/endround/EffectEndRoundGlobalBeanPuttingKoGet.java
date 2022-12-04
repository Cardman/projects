package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundGlobalBeanPuttingKoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getPuttingKo());
    }
}
