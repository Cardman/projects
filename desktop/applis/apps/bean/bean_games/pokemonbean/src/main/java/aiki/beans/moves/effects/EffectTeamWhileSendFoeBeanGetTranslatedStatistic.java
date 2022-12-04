package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanGetTranslatedStatistic implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedStatistic(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
