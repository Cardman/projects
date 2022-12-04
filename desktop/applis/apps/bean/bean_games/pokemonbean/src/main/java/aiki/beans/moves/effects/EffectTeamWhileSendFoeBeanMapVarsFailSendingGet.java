package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanMapVarsFailSendingGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsFailSending());
    }
}
