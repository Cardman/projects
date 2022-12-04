package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class DealerBeanGetAllTm implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (DealerBean) ((PokemonBeanStruct)_instance).getInstance()).getAllTmDealer());
    }
}
