package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanStatusByNbUsesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getShortStr(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getStatusByNbUses());
    }
}
