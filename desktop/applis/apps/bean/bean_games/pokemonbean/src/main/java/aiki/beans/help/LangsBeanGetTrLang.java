package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class LangsBeanGetTrLang implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (LangsBean) ((PokemonBeanStruct)_instance).getInstance()).getTrLang(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
