package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundIndividualBeanIsType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).isType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
