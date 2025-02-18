package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanDeletedByFoeTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getDeletedByFoeTypes());
    }
}
