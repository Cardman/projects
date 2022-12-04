package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundIndividualBeanClickUserStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).clickUserStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
