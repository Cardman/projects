package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveBeanRepeatRoundLawGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getLgIntRate(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getRepeatRoundLaw());
    }
}
