package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class EffectTeamBeanClickDisableFoeTeamStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).clickDisableFoeTeamStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
