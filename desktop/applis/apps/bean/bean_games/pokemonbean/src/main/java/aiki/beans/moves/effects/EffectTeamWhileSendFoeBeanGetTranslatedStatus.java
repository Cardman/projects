package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanGetTranslatedStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
