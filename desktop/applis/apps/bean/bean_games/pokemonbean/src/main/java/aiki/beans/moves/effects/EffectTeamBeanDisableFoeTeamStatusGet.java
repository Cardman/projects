package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamBeanDisableFoeTeamStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getDisableFoeTeamStatus());
    }
}
