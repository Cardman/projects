package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GeneralHelpBeanGetTrTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getTrTm(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
