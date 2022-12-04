package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class FightHelpBeanGetTrAbilitiesSentBeginOth implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTrAbilitiesSentBeginOth(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
