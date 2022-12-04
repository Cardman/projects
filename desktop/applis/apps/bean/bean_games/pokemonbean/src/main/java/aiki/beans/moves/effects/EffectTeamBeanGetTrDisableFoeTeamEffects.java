package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamBeanGetTrDisableFoeTeamEffects implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getTrDisableFoeTeamEffects(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
