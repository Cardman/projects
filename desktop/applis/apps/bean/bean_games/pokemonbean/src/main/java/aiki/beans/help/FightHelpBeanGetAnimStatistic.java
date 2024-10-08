package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class FightHelpBeanGetAnimStatistic implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getAnimStatistic(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
