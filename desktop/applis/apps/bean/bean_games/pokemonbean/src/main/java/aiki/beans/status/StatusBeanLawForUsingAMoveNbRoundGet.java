package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanLawForUsingAMoveNbRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getLgIntRate(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getLawForUsingAMoveNbRound());
    }
}
