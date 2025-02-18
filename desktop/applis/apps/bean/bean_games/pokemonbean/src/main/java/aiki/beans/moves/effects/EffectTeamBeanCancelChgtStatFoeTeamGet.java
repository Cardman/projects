package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectTeamBeanCancelChgtStatFoeTeamGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getCancelChgtStatFoeTeam());
    }
}
