package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundPositionTargetBeanClickTargetRelationMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundPositionTargetBean) ((PokemonBeanStruct)_instance).getInstance()).clickTargetRelationMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
