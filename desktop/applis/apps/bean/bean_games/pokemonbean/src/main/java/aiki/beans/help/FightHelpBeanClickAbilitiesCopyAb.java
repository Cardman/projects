package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanClickAbilitiesCopyAb implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbilitiesCopyAb(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
