package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class TeamBeanGetPlayerFigtherAgainstFoe implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getPlayerFigtherAgainstFoe(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
