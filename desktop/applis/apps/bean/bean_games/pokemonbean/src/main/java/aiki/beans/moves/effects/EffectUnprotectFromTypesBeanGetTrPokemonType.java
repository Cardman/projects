package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectUnprotectFromTypesBeanGetTrPokemonType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrPokemonType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
