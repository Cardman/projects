package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectTeamWhileSendFoeBeanGetTranslatedType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
