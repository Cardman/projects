package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanReasonsEndRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getReasonsEndRound());
    }
}
