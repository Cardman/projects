package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCopyMoveBeanClickMoveTrans implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickMoveTrans(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
