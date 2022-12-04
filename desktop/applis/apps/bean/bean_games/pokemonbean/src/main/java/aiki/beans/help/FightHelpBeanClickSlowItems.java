package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanClickSlowItems implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).clickSlowItems(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
