package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanGetTrAbilitiesRevAbs implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTrAbilitiesRevAbs(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
