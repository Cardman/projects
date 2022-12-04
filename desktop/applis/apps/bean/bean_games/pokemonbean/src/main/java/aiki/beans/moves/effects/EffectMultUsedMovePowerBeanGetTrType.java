package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectMultUsedMovePowerBeanGetTrType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectMultMovePowerBean) ((PokemonBeanStruct)_instance).getInstance()).getTrType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
