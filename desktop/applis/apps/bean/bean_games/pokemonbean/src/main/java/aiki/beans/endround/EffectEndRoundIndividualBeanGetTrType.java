package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundIndividualBeanGetTrType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).getTrType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
