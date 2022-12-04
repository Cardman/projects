package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectProtectFromTypesBeanGetTrType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectProtectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTrType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
