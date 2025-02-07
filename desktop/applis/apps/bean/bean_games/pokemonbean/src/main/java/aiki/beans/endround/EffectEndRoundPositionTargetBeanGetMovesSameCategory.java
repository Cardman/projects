package aiki.beans.endround;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectEndRoundPositionTargetBeanGetMovesSameCategory implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectEndRoundPositionTargetBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesSameCategory());
    }
}
