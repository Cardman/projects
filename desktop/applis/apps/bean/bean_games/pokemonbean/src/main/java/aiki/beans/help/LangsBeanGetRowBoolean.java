package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class LangsBeanGetRowBoolean implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (LangsBean) ((PokemonBeanStruct)_instance).getInstance()).getRowBoolean(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
