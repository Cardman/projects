package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class LangsBeanGetRowDesc implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (LangsBean) ((PokemonBeanStruct)_instance).getInstance()).getRowDesc(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
